const API_URL = "/api/properties";

// Load properties when page opens
document.addEventListener("DOMContentLoaded", () => {
    loadProperties();

    const form = document.getElementById("propertyForm");

    if (form) {
        form.addEventListener("submit", addProperty);
    }
});


// ===============================
// GET - Load all properties
// ===============================
async function loadProperties() {
    try {
        const response = await fetch(API_URL);

        if (!response.ok) {
            throw new Error("Failed to load properties");
        }

        const properties = await response.json();

        displayProperties(properties);

    } catch (error) {
        console.error("Error loading properties:", error);

        const propertyList = document.getElementById("propertyList");

        if (propertyList) {
            propertyList.innerHTML = `
                <p class="error">
                    Unable to load properties.
                </p>
            `;
        }
    }
}


// ===============================
// POST - Add property
// ===============================
async function addProperty(event) {
    event.preventDefault();

    const property = {
        title: document.getElementById("title").value,
        location: document.getElementById("location").value,
        propertyType: document.getElementById("propertyType").value,
        listingType: document.getElementById("listingType").value,
        price: Number(document.getElementById("price").value),
        bedrooms: Number(document.getElementById("bedrooms").value),
        bathrooms: Number(document.getElementById("bathrooms").value),
        area: Number(document.getElementById("area").value),
        description: document.getElementById("description").value
    };

    try {
        const response = await fetch(API_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(property)
        });

        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(errorText || "Failed to add property");
        }

        const savedProperty = await response.json();

        alert("Property added successfully!");

        document.getElementById("propertyForm").reset();

        loadProperties();

        console.log("Saved property:", savedProperty);

    } catch (error) {
        console.error("Error adding property:", error);

        alert("Failed to add property. Check the backend.");
    }
}


// ===============================
// Display properties
// ===============================
function displayProperties(properties) {

    const propertyList = document.getElementById("propertyList");

    if (!propertyList) {
        return;
    }

    propertyList.innerHTML = "";

    if (!properties || properties.length === 0) {
        propertyList.innerHTML = `
            <p>No properties available.</p>
        `;
        return;
    }

    properties.forEach(property => {

        const card = document.createElement("div");

        card.className = "property-card";

        card.innerHTML = `
            <h3>${escapeHtml(property.title || "")}</h3>

            <p>
                <strong>Location:</strong>
                ${escapeHtml(property.location || "")}
            </p>

            <p>
                <strong>Property Type:</strong>
                ${escapeHtml(property.propertyType || "")}
            </p>

            <p>
                <strong>Listing:</strong>
                ${escapeHtml(property.listingType || "")}
            </p>

            <p>
                <strong>Price:</strong>
                ₹${formatPrice(property.price)}
            </p>

            <p>
                <strong>Bedrooms:</strong>
                ${property.bedrooms ?? "-"}
            </p>

            <p>
                <strong>Bathrooms:</strong>
                ${property.bathrooms ?? "-"}
            </p>

            <p>
                <strong>Area:</strong>
                ${property.area ?? "-"} sq.ft
            </p>

            <p>
                <strong>Description:</strong>
                ${escapeHtml(property.description || "")}
            </p>
        `;

        propertyList.appendChild(card);
    });
}


// ===============================
// Format price
// ===============================
function formatPrice(price) {

    if (price === null || price === undefined) {
        return "N/A";
    }

    return Number(price).toLocaleString("en-IN");
}


// ===============================
// Basic HTML escaping
// ===============================
function escapeHtml(value) {

    return String(value)
        .replace(/&/g, "&amp;")
        .replace(/</g, "&lt;")
        .replace(/>/g, "&gt;")
        .replace(/"/g, "&quot;")
        .replace(/'/g, "&#039;");
}
