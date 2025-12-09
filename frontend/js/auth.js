const API_URL = 'http://localhost:8080/api';

// Show/Hide Forms
function showRegister() {
    document.getElementById('loginForm').style.display = 'none';
    document.getElementById('registerForm').style.display = 'block';
    document.querySelectorAll('.btn-nav').forEach(btn => btn.classList.remove('active-tab'));
    document.querySelectorAll('.btn-nav')[1].classList.add('active-tab');
}

function showLogin() {
    document.getElementById('registerForm').style.display = 'none';
    document.getElementById('loginForm').style.display = 'block';
    document.querySelectorAll('.btn-nav').forEach(btn => btn.classList.remove('active-tab'));
    document.querySelectorAll('.btn-nav')[0].classList.add('active-tab');
}

// Register User
async function register() {
    const user = {
        name: document.getElementById('regName').value,
        email: document.getElementById('regEmail').value,
        password: document.getElementById('regPassword').value,
        phone: document.getElementById('regPhone').value,
        address: document.getElementById('regAddress').value
    };

    try {
        const response = await fetch(`${API_URL}/users/register`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(user)
        });

        const data = await response.json();

        if (response.ok) {
            showMessage('Registration successful! Please login.', 'success');
            setTimeout(showLogin, 2000);
        } else {
            showMessage(data, 'error');
        }
    } catch (error) {
        showMessage('Error: ' + error.message, 'error');
    }
}

// Login User
async function login() {
    const credentials = {
        email: document.getElementById('loginEmail').value,
        password: document.getElementById('loginPassword').value
    };

    try {
        const response = await fetch(`${API_URL}/users/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(credentials)
        });

        const data = await response.json();

        if (response.ok) {
            // Save user data in localStorage
            localStorage.setItem('user', JSON.stringify(data));
            showMessage('Login successful!', 'success');
            
            // Redirect based on role
            setTimeout(() => {
                if (data.role === 'ADMIN') {
                    window.location.href = 'admin.html';
                } else {
                    window.location.href = 'index.html';
                }
            }, 1000);
        } else {
            showMessage(data, 'error');
        }
    } catch (error) {
        showMessage('Error: ' + error.message, 'error');
    }
}

// Show Message
function showMessage(msg, type) {
    const messageDiv = document.getElementById('message');
    messageDiv.textContent = msg;
    messageDiv.className = `message ${type}`;
}
