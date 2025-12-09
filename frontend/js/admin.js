const API_URL = 'http://localhost:8080/api';
let uploadedImageUrl = '';

window.onload = function() {
    const user = JSON.parse(localStorage.getItem('user'));
    if (!user || user.role !== 'ADMIN') {
        alert('Admin access only!');
        window.location.href = 'login.html';
        return;
    }
    loadFoods();
};

// Preview uploaded image
function previewImage() {
    const file = document.getElementById('foodImageFile').files[0];
    const preview = document.getElementById('imagePreview');
    
    if (file) {
        const reader = new FileReader();
        reader.onload = function(e) {
            uploadedImageUrl = e.target.result;
            preview.innerHTML = `<img src="${e.target.result}" style="max-width: 200px; border-radius: 10px; margin-top: 10px;" alt="Preview">`;
        };
        reader.readAsDataURL(file);
    }
}

// Show tabs
function showTab(tab) {
    document.getElementById('foodsTab').style.display = 'none';
    document.getElementById('ordersTab').style.display = 'none';
    
    if (tab === 'foods') {
        document.getElementById('foodsTab').style.display = 'block';
        loadFoods();
    } else {
        document.getElementById('ordersTab').style.display = 'block';
        loadAllOrders();
    }
}

// Add or Update food
async function addFood() {
    const imageUrlInput = document.getElementById('foodImage').value;
    const finalImageUrl = uploadedImageUrl || imageUrlInput || 'https://via.placeholder.com/300x200?text=Food';
    const isAvailable = document.getElementById('foodAvailable').checked;
    
    const food = {
        name: document.getElementById('foodName').value,
        description: document.getElementById('foodDesc').value,
        price: parseFloat(document.getElementById('foodPrice').value),
        category: document.getElementById('foodCategory').value,
        imageUrl: finalImageUrl,
        available: isAvailable
    };

    try {
        let response;
        if (editingFoodId) {
            // Update existing food
            response = await fetch(`${API_URL}/foods/${editingFoodId}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(food)
            });
        } else {
            // Add new food
            response = await fetch(`${API_URL}/foods`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(food)
            });
        }

        if (response.ok) {
            alert(editingFoodId ? 'Food updated successfully!' : 'Food added successfully!');
            loadFoods();
            clearForm();
        }
    } catch (error) {
        alert('Error: ' + error.message);
    }
}

// Clear form and reset to add mode
function clearForm() {
    document.getElementById('foodName').value = '';
    document.getElementById('foodDesc').value = '';
    document.getElementById('foodPrice').value = '';
    document.getElementById('foodCategory').value = '';
    document.getElementById('foodImage').value = '';
    document.getElementById('foodImageFile').value = '';
    document.getElementById('imagePreview').innerHTML = '';
    document.getElementById('foodAvailable').checked = true;
    uploadedImageUrl = '';
    editingFoodId = null;
    
    const addButton = document.querySelector('.form-group button');
    addButton.textContent = 'Add Food';
    addButton.style.background = '#FF6B35';
}

// Load all foods
async function loadFoods() {
    try {
        const response = await fetch(`${API_URL}/foods`);
        const foods = await response.json();
        displayAdminFoods(foods);
    } catch (error) {
        console.error('Error:', error);
    }
}

// Display foods for admin
function displayAdminFoods(foods) {
    const listDiv = document.getElementById('adminFoodList');
    listDiv.innerHTML = '';

    foods.forEach(food => {
        const availabilityBadge = food.available 
            ? '<span style="background: #28a745; color: white; padding: 4px 12px; border-radius: 15px; font-size: 0.85rem;">Available</span>'
            : '<span style="background: #dc3545; color: white; padding: 4px 12px; border-radius: 15px; font-size: 0.85rem;">Unavailable</span>';
        
        const foodHTML = `
            <div class="food-card">
                <img src="${food.imageUrl}" alt="${food.name}" style="width: 100%; height: 150px; object-fit: cover; border-radius: 10px;">
                <h3>${food.name}</h3>
                <p>${food.description}</p>
                <p><strong>Price:</strong> ₹${food.price}</p>
                <p><strong>Category:</strong> ${food.category}</p>
                <p><strong>Status:</strong> ${availabilityBadge}</p>
                <div style="display: flex; gap: 8px; margin-top: 10px; flex-wrap: wrap;">
                    <button onclick="editFood('${food.id}')" style="flex: 1; background: #007bff; min-width: 80px;">Edit</button>
                    <button onclick="toggleAvailability('${food.id}', ${!food.available})" style="flex: 1; background: ${food.available ? '#ffc107' : '#28a745'}; min-width: 80px;">
                        ${food.available ? 'Unavailable' : 'Available'}
                    </button>
                    <button onclick="deleteFood('${food.id}')" style="flex: 1; background: #dc3545; min-width: 80px;">Delete</button>
                </div>
            </div>
        `;
        listDiv.innerHTML += foodHTML;
    });
}

// Edit food
let editingFoodId = null;

async function editFood(id) {
    try {
        const response = await fetch(`${API_URL}/foods/${id}`);
        const food = await response.json();
        
        // Populate form with existing data
        document.getElementById('foodName').value = food.name;
        document.getElementById('foodDesc').value = food.description;
        document.getElementById('foodPrice').value = food.price;
        document.getElementById('foodCategory').value = food.category;
        document.getElementById('foodImage').value = food.imageUrl;
        document.getElementById('foodAvailable').checked = food.available;
        
        // Change button to Update
        editingFoodId = id;
        const addButton = document.querySelector('.form-group button');
        addButton.textContent = 'Update Food';
        addButton.style.background = '#007bff';
        
        // Scroll to form
        document.querySelector('.form-group').scrollIntoView({ behavior: 'smooth' });
    } catch (error) {
        alert('Error loading food: ' + error.message);
    }
}

// Toggle food availability
async function toggleAvailability(id, newStatus) {
    try {
        const response = await fetch(`${API_URL}/foods/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ available: newStatus })
        });

        if (response.ok) {
            alert('Availability updated!');
            loadFoods();
        }
    } catch (error) {
        alert('Error: ' + error.message);
    }
}

// Delete food
async function deleteFood(id) {
    if (!confirm('Delete this food item?')) return;

    try {
        const response = await fetch(`${API_URL}/foods/${id}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            alert('Food deleted!');
            loadFoods();
        }
    } catch (error) {
        alert('Error: ' + error.message);
    }
}

// Load all orders
async function loadAllOrders() {
    try {
        const response = await fetch(`${API_URL}/orders`);
        const orders = await response.json();
        displayAdminOrders(orders);
    } catch (error) {
        console.error('Error:', error);
    }
}

// Display orders for admin
function displayAdminOrders(orders) {
    const listDiv = document.getElementById('adminOrdersList');
    listDiv.innerHTML = '';

    orders.forEach(order => {
        const date = new Date(order.orderDate).toLocaleString();
        
        const orderHTML = `
            <div class="order-card">
                <h3>Order #${order.id.substring(0, 8)}</h3>
                <p><strong>Date:</strong> ${date}</p>
                <p><strong>Total:</strong> ₹${order.totalAmount}</p>
                <p><strong>Status:</strong> ${order.status}</p>
                <select onchange="updateStatus('${order.id}', this.value)">
                    <option value="PENDING" ${order.status === 'PENDING' ? 'selected' : ''}>Pending</option>
                    <option value="CONFIRMED" ${order.status === 'CONFIRMED' ? 'selected' : ''}>Confirmed</option>
                    <option value="DELIVERED" ${order.status === 'DELIVERED' ? 'selected' : ''}>Delivered</option>
                </select>
            </div>
        `;
        listDiv.innerHTML += orderHTML;
    });
}

// Update order status
async function updateStatus(orderId, newStatus) {
    try {
        const response = await fetch(`${API_URL}/orders/${orderId}/status`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ status: newStatus })
        });

        if (response.ok) {
            alert('Status updated successfully!');
            loadAllOrders();
        } else {
            const error = await response.text();
            alert('Error: ' + error);
        }
    } catch (error) {
        alert('Error: ' + error.message);
    }
}

function logout() {
    localStorage.removeItem('user');
    window.location.href = 'login.html';
}
