let authToken = '';

async function signup() {
    const name = document.getElementById('signup-name').value;
    const password = document.getElementById('signup-password').value;

    const res = await fetch('/public/createUser', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name, password })
    });
    if (res.ok) {
        alert('User created! You can log in now.');
    } else {
        alert('Failed to create user');
    }
}

function getAuthHeader() {
    const user = document.getElementById('login-name').value;
    const pass = document.getElementById('login-password').value;
    return 'Basic ' + btoa(user + ':' + pass);
}

async function login() {
    authToken = getAuthHeader();
    const res = await fetch('/journal/getAllJournals', {
        headers: { 'Authorization': authToken }
    });
    if (res.ok) {
        document.getElementById('auth-section').classList.add('hidden');
        document.getElementById('journal-section').classList.remove('hidden');
        const data = await res.json();
        renderEntries(data);
    } else {
        alert('Login failed');
    }
}

async function addEntry() {
    const title = document.getElementById('entry-title').value;
    const content = document.getElementById('entry-content').value;

    const res = await fetch('/journal/createJournal', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': authToken
        },
        body: JSON.stringify({ title, content })
    });
    if (res.ok) {
        const entry = await res.json();
        renderEntries([entry], true);
        document.getElementById('entry-title').value = '';
        document.getElementById('entry-content').value = '';
    } else {
        alert('Failed to save entry');
    }
}

function renderEntries(entries, append = false) {
    const container = document.getElementById('entries');
    if (!append) container.innerHTML = '';
    entries.forEach(e => {
        const div = document.createElement('div');
        div.className = 'entry';
        div.innerHTML = `<h4>${e.title}</h4><p>${e.content}</p><small>${e.date}</small>`;
        container.prepend(div);
    });
}

document.getElementById('signup-btn').addEventListener('click', signup);
document.getElementById('login-btn').addEventListener('click', login);
document.getElementById('add-entry-btn').addEventListener('click', addEntry);
