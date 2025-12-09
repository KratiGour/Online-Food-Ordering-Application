const API_URL = 'http://localhost:8080/api';

window.onload = function() {
    const user = JSON.parse(localStorage.getItem('user'));
    if (!user) {
        window.location.href = 'login.html';
        return;
    }
    loadOrders();
};

// Load user's orders
async function loadOrders() {
    const user = JSON.parse(localStorage.getItem('user'));
    
    try {
        const response = await fetch(`${API_URL}/orders/user/${user.id}`);
        const orders = await response.json();
        displayOrders(orders);
    } catch (error) {
        console.error('Error loading orders:', error);
    }
}

// Display orders
function displayOrders(orders) {
    const ordersDiv = document.getElementById('ordersList');
    
    if (orders.length === 0) {
        ordersDiv.innerHTML = '<p>No orders yet</p>';
        return;
    }

    ordersDiv.innerHTML = '';

    orders.forEach(order => {
        const date = new Date(order.orderDate).toLocaleString();
        const statusClass = `status-${order.status.toLowerCase()}`;

        let itemsList = '';
        order.items.forEach(item => {
            itemsList += `<li>${item.foodName} × ${item.quantity} = ₹${item.price * item.quantity}</li>`;
        });

        const orderHTML = `
            <div class="order-card">
                <h3>Order #${order.id.substring(0, 8)}</h3>
                <p><strong>Date:</strong> ${date}</p>
                <p><strong>Status:</strong> <span class="order-status ${statusClass}">${order.status}</span></p>
                <p><strong>Items:</strong></p>
                <ul>${itemsList}</ul>
                <p><strong>Total:</strong> ₹${order.totalAmount}</p>
                <p><strong>Delivery Address:</strong> ${order.deliveryAddress}</p>
            </div>
        `;
        ordersDiv.innerHTML += orderHTML;
    });
}

function logout() {
    localStorage.removeItem('user');
    localStorage.removeItem('cart');
    window.location.href = 'login.html';
}
