const API_URL = 'http://localhost:8080/api';
let allFoods = [];

// Check if user is logged in
window.onload = function() {
    const user = JSON.parse(localStorage.getItem('user'));
    if (!user) {
        window.location.href = 'login.html';
        return;
    }
    document.getElementById('userName').textContent = `Hello, ${user.name}`;
    loadFoods();
    updateCartCount();
};

// Load all foods
async function loadFoods() {
    try {
        const response = await fetch(`${API_URL}/foods/available`);
        allFoods = await response.json();
        displayFoods(allFoods);
    } catch (error) {
        console.error('Error loading foods:', error);
    }
}

// Display foods
function displayFoods(foods) {
    const grid = document.getElementById('foodGrid');
    grid.innerHTML = '';

    foods.forEach(food => {
        const card = `
            <div class="food-card">
                <img src="${food.imageUrl || 'https://via.placeholder.com/300x200?text=Food'}" alt="${food.name}">
                <div class="food-info">
                    <h3>${food.name}</h3>
                    <p>${food.description}</p>
                    <div class="food-price">₹${food.price}</div>
                    <button onclick="addToCart('${food.id}', '${food.name}', ${food.price})">Add to Cart</button>
                </div>
            </div>
        `;
        grid.innerHTML += card;
    });
}

// Search food
function searchFood() {
    const keyword = document.getElementById('searchInput').value.toLowerCase();
    const filtered = allFoods.filter(food => 
        food.name.toLowerCase().includes(keyword) || 
        food.description.toLowerCase().includes(keyword)
    );
    displayFoods(filtered);
}

// Filter by category
function filterByCategory(category) {
    if (category === 'all') {
        displayFoods(allFoods);
    } else {
        const filtered = allFoods.filter(food => food.category === category);
        displayFoods(filtered);
    }
}

// Add to cart
function addToCart(foodId, foodName, price) {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    
    const existingItem = cart.find(item => item.foodId === foodId);
    if (existingItem) {
        existingItem.quantity++;
    } else {
        cart.push({ foodId, foodName, price, quantity: 1 });
    }
    
    localStorage.setItem('cart', JSON.stringify(cart));
    updateCartCount();
    alert(`${foodName} added to cart!`);
}

// Update cart count
function updateCartCount() {
    const cart = JSON.parse(localStorage.getItem('cart')) || [];
    const count = cart.reduce((sum, item) => sum + item.quantity, 0);
    document.getElementById('cartCount').textContent = count;
}

// Logout
function logout() {
    localStorage.removeItem('user');
    localStorage.removeItem('cart');
    window.location.href = 'login.html';
}
