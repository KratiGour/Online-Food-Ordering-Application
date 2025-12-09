const API_URL = 'http://localhost:8080/api';

window.onload = function() {
    const user = JSON.parse(localStorage.getItem('user'));
    if (!user) {
        window.location.href = 'login.html';
        return;
    }
    displayCart();
};

// Display cart items
function displayCart() {
    const cart = JSON.parse(localStorage.getItem('cart')) || [];
    const cartDiv = document.getElementById('cartItems');
    
    if (cart.length === 0) {
        cartDiv.innerHTML = '<p>Your cart is empty</p>';
        document.getElementById('totalAmount').textContent = '0';
        return;
    }

    let total = 0;
    cartDiv.innerHTML = '';

    cart.forEach((item, index) => {
        const subtotal = item.price * item.quantity;
        total += subtotal;

        const itemHTML = `
            <div class="cart-item">
                <div>
                    <h3>${item.foodName}</h3>
                    <p>₹${item.price} × ${item.quantity} = ₹${subtotal}</p>
                </div>
                <div>
                    <button onclick="updateQuantity(${index}, -1)">-</button>
                    <span>${item.quantity}</span>
                    <button onclick="updateQuantity(${index}, 1)">+</button>
                    <button onclick="removeItem(${index})">Remove</button>
                </div>
            </div>
        `;
        cartDiv.innerHTML += itemHTML;
    });

    document.getElementById('totalAmount').textContent = total;
}

// Update quantity
function updateQuantity(index, change) {
    let cart = JSON.parse(localStorage.getItem('cart'));
    cart[index].quantity += change;
    
    if (cart[index].quantity <= 0) {
        cart.splice(index, 1);
    }
    
    localStorage.setItem('cart', JSON.stringify(cart));
    displayCart();
}

// Remove item
function removeItem(index) {
    let cart = JSON.parse(localStorage.getItem('cart'));
    cart.splice(index, 1);
    localStorage.setItem('cart', JSON.stringify(cart));
    displayCart();
}

// Clear cart
function clearCart() {
    if (confirm('Clear all items from cart?')) {
        localStorage.removeItem('cart');
        displayCart();
    }
}

// Place order
async function placeOrder() {
    const user = JSON.parse(localStorage.getItem('user'));
    const cart = JSON.parse(localStorage.getItem('cart')) || [];

    if (cart.length === 0) {
        alert('Cart is empty!');
        return;
    }

    const order = {
        userId: user.id,
        items: cart.map(item => ({
            foodId: item.foodId,
            quantity: item.quantity
        })),
        deliveryAddress: user.address
    };

    try {
        const response = await fetch(`${API_URL}/orders`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(order)
        });

        if (response.ok) {
            alert('Order placed successfully!');
            localStorage.removeItem('cart');
            window.location.href = 'orders.html';
        } else {
            const error = await response.text();
            alert('Error: ' + error);
        }
    } catch (error) {
        alert('Error placing order: ' + error.message);
    }
}

function logout() {
    localStorage.removeItem('user');
    localStorage.removeItem('cart');
    window.location.href = 'login.html';
}
