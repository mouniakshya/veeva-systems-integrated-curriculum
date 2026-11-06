/**
 * BharatMart India - Client Application
 * Implements all 12 Core Java E-Commerce Requirements in a Human-Made, Indian-Localized Interface
 */

// ==========================================
// 1. Initial State & Data Pool (Indian Market)
// ==========================================

const INITIAL_PRODUCTS = [
  {
    productId: "P101",
    name: "iPhone 15 Pro",
    description: "Apple flagship smartphone with A17 Pro chip, Titanium build, and 48MP camera (128GB)",
    price: 127990.00,
    mrp: 134900.00,
    stockQuantity: 20,
    companyName: "Apple",
    category: "Mobiles",
    rating: 4.8,
    reviews: 2410,
    badge: "🔥 Bestseller",
    colorVariant: "Natural Titanium"
  },
  {
    productId: "P102",
    name: "iPhone 14",
    description: "Previous gen Apple phone with Super Retina XDR OLED display, A15 Bionic (128GB)",
    price: 56999.00,
    mrp: 69900.00,
    stockQuantity: 30,
    companyName: "Apple",
    category: "Mobiles",
    rating: 4.7,
    reviews: 4120,
    badge: "⚡ Festive Deal",
    colorVariant: "Starlight White"
  },
  {
    productId: "P103",
    name: "Galaxy S24 Ultra",
    description: "Samsung flagship AI smartphone with integrated S-Pen, 200MP Quad Telephoto (512GB)",
    price: 129999.00,
    mrp: 144999.00,
    stockQuantity: 15,
    companyName: "Samsung",
    category: "Mobiles",
    rating: 4.8,
    reviews: 1890,
    badge: "🔥 Bestseller",
    colorVariant: "Titanium Gray"
  },
  {
    productId: "P104",
    name: "Galaxy Book4 Pro",
    description: "Samsung thin & light laptop powered by Intel Core Ultra 7, 16GB LPDDR5X, 512GB SSD",
    price: 119990.00,
    mrp: 149990.00,
    stockQuantity: 10,
    companyName: "Samsung",
    category: "Computers",
    rating: 4.6,
    reviews: 640,
    badge: "Trending",
    colorVariant: "Moonstone Gray"
  },
  {
    productId: "P105",
    name: "Sony WH-1000XM5",
    description: "Industry-leading noise cancelling wireless headphones with 30-hour battery and Speak-to-Chat",
    price: 26990.00,
    mrp: 34990.00,
    stockQuantity: 40,
    companyName: "Sony",
    category: "Audio",
    rating: 4.9,
    reviews: 3280,
    badge: "Amazon's Choice",
    colorVariant: "Matte Black"
  },
  {
    productId: "P106",
    name: "Sony Bravia 55 OLED",
    description: "4K Ultra HD Smart Google TV with Dolby Vision, Atmos, and Cognitive Processor XR",
    price: 54990.00,
    mrp: 99900.00,
    stockQuantity: 8,
    companyName: "Sony",
    category: "Electronics",
    rating: 4.8,
    reviews: 1150,
    badge: "45% Off Festive",
    colorVariant: "Black Titanium"
  },
  {
    productId: "P107",
    name: "MacBook Air M3",
    description: "13.6-inch lightweight Apple laptop with M3 chip, 8GB Unified Memory, 256GB SSD",
    price: 104900.00,
    mrp: 114900.00,
    stockQuantity: 12,
    companyName: "Apple",
    category: "Computers",
    rating: 4.9,
    reviews: 2110,
    badge: "Top Rated ★",
    colorVariant: "Midnight Blue"
  },
  {
    productId: "P108",
    name: "Echo Dot 5th Gen",
    description: "Smart speaker with Alexa voice assistant, deeper bass, and motion detection for smart home",
    price: 4499.00,
    mrp: 5499.00,
    stockQuantity: 60,
    companyName: "Amazon",
    category: "Smart Home",
    rating: 4.6,
    reviews: 8420,
    badge: "Smart Home",
    colorVariant: "Deep Sea Blue"
  },
  {
    productId: "P109",
    name: "OnePlus 12 5G",
    description: "Flagship Snapdragon 8 Gen 3, Hasselblad Camera, 100W SUPERVOOC charging (16GB/512GB)",
    price: 64999.00,
    mrp: 69999.00,
    stockQuantity: 35,
    companyName: "OnePlus",
    category: "Mobiles",
    rating: 4.7,
    reviews: 1540,
    badge: "Flagship Killer",
    colorVariant: "Flowy Emerald"
  },
  {
    productId: "P110",
    name: "boAt Airdopes 141",
    description: "ANC True Wireless Earbuds with 42H Total Playtime, Beast Mode for Gaming (Gunmetal Black)",
    price: 1499.00,
    mrp: 4490.00,
    stockQuantity: 100,
    companyName: "boAt",
    category: "Audio",
    rating: 4.4,
    reviews: 12900,
    badge: "67% Mega Savings",
    colorVariant: "Bold Black"
  }
];

const INITIAL_CUSTOMERS = [
  {
    id: "CUST-001",
    name: "Aarav Sharma",
    email: "aarav.sharma@gmail.com",
    phone: "+91 98112 34567",
    city: "New Delhi",
    address: { street: "Flat 402, Block B, Connaught Place", city: "New Delhi", state: "Delhi", zipCode: "110001", country: "India" },
    subscription: null // or { tier: "ANNUALLY", expires: "2027-10-31" }
  },
  {
    id: "CUST-002",
    name: "Priya Patel",
    email: "priya.patel@gmail.com",
    phone: "+91 98450 12345",
    city: "Bengaluru",
    address: { street: "74, 4th Cross, 12th Main, Indiranagar", city: "Bengaluru", state: "Karnataka", zipCode: "560038", country: "India" },
    subscription: null
  },
  {
    id: "CUST-003",
    name: "Rohan Mehta",
    email: "rohan.mehta@yahoo.in",
    phone: "+91 98200 98765",
    city: "Mumbai",
    address: { street: "12/A, Sea Face Apartments, Bandra West", city: "Mumbai", state: "Maharashtra", zipCode: "400050", country: "India" },
    subscription: null
  },
  {
    id: "CUST-004",
    name: "Ananya Iyer",
    email: "ananya.iyer@outlook.com",
    phone: "+91 99490 87654",
    city: "Hyderabad",
    address: { street: "Plot 21, Road No. 3, Banjara Hills", city: "Hyderabad", state: "Telangana", zipCode: "500034", country: "India" },
    subscription: null
  }
];

const INITIAL_GIFT_CARDS = [
  { code: "DIWALI500", pin: "1234", balance: 500.00, active: true },
  { code: "FESTIVE2000", pin: "5678", balance: 2000.00, active: true },
  { code: "MEGA5000", pin: "9999", balance: 5000.00, active: true }
];

const PIN_DATABASE = {
  "110001": { city: "New Delhi (Connaught Place)", state: "Delhi", eta: "Tomorrow by 11:00 AM", express: true },
  "110020": { city: "New Delhi (Okhla)", state: "Delhi", eta: "Tomorrow by 1:00 PM", express: true },
  "110016": { city: "New Delhi (Hauz Khas)", state: "Delhi", eta: "Tomorrow by 11:00 AM", express: true },
  "110092": { city: "East Delhi (Laxmi Nagar)", state: "Delhi", eta: "Tomorrow by 2:00 PM", express: true },
  "201301": { city: "Noida Sector 18", state: "Uttar Pradesh", eta: "Tomorrow by 12:00 PM", express: true },
  "122001": { city: "Gurugram Cyber City", state: "Haryana", eta: "Tomorrow by 11:30 AM", express: true },
  "560038": { city: "Bengaluru (Indiranagar)", state: "Karnataka", eta: "In 2 Days", express: false },
  "400050": { city: "Mumbai (Bandra West)", state: "Maharashtra", eta: "In 2 Days", express: false },
  "500034": { city: "Hyderabad (Banjara Hills)", state: "Telangana", eta: "In 2 Days", express: false }
};

// Global State
let state = {
  currentSession: "aarav", // "aarav" | "priya" | "rohan" | "ananya" | "admin"
  currentPincode: "110001",
  products: [...INITIAL_PRODUCTS],
  customers: [...INITIAL_CUSTOMERS],
  giftCards: [...INITIAL_GIFT_CARDS],
  carts: {
    "CUST-001": [],
    "CUST-002": [],
    "CUST-003": [],
    "CUST-004": []
  },
  orders: [],
  selectedPaymentMethod: "upi",
  activeCompanyFilter: "All"
};

// Helper to format in Indian Rupees
function formatINR(val) {
  if (isNaN(val)) return "₹0.00";
  return "₹" + Number(val).toLocaleString("en-IN", {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2
  });
}

// ==========================================
// 2. Initialization & Session Management
// ==========================================

document.addEventListener("DOMContentLoaded", () => {
  renderCatalog();
  updateCartUI();
  updateSessionUI();
  renderAdminOrders();
  renderAdminCustomers();
  renderActiveGiftCards();
  checkPincodeSpeed();
});

function switchSession(userKey) {
  state.currentSession = userKey;
  updateSessionUI();

  if (userKey === "admin") {
    switchTab("adminTab");
    showToast("Switched to Delhi Central Operations Console", "🛡️");
  } else {
    switchTab("catalogTab");
    const cust = getCurrentCustomer();
    showToast(`Welcome back, ${cust.name}! Delivering to ${cust.city}`, "👤");
  }

  updateCartUI();
  renderOrders();
  renderCatalog();
}

function getCurrentCustomer() {
  const map = {
    aarav: "CUST-001",
    priya: "CUST-002",
    rohan: "CUST-003",
    ananya: "CUST-004"
  };
  const custId = map[state.currentSession] || "CUST-001";
  return state.customers.find(c => c.id === custId);
}

function updateSessionUI() {
  const cust = getCurrentCustomer();
  const topPill = document.getElementById("topMembershipPill");
  const memberStatus = document.getElementById("memberStatusText");
  const subStatusCard = document.getElementById("currentSubCard");
  const cartLabel = document.getElementById("cartUserLabel");
  const addressBox = document.getElementById("checkoutAddressBox");
  const navLoc = document.getElementById("navLocMain");

  if (state.currentSession === "admin") {
    topPill.innerHTML = `🛡️ Central Operations Hub • Delhi Region • Full Admin Access`;
    topPill.style.background = "rgba(239, 68, 68, 0.3)";
    return;
  }

  if (cust) {
    state.currentPincode = cust.address.zipCode;
    navLoc.textContent = `${cust.name.split(' ')[0]} - ${cust.city} ${cust.address.zipCode}`;
    cartLabel.textContent = `${cust.name} - ${cust.city} (${cust.address.zipCode})`;
    addressBox.textContent = `${cust.address.street}, ${cust.address.city}, ${cust.address.state} - ${cust.address.zipCode}, ${cust.address.country}`;

    if (cust.subscription) {
      memberStatus.innerHTML = `⭐ <strong>BharatPrime VIP Member</strong> (₹0 Free Express Delivery)`;
      topPill.style.background = "rgba(16, 185, 129, 0.3)";

      subStatusCard.innerHTML = `
        <div style="color: #6ee7b7; font-weight: 800; font-size: 16px;">✓ Active ${cust.subscription.tier} VIP Membership</div>
        <p style="font-size: 13px; color: #cbd5e1; margin-top: 4px;">Valid until <strong>${cust.subscription.expires}</strong>. You enjoy 100% Free Express Delivery across Delhi NCR &amp; India on every single order!</p>
      `;
    } else {
      memberStatus.innerHTML = `Standard Delivery • ₹99.00`;
      topPill.style.background = "rgba(255, 255, 255, 0.22)";

      subStatusCard.innerHTML = `
        <div style="font-weight: 700; font-size: 15px;">Standard Account (Non-Prime)</div>
        <p style="font-size: 13px; color: #94a3b8; margin-top: 4px;">Standard delivery charge is ₹99.00 per order. Subscribe below to get 100% Free Express Delivery on all orders!</p>
      `;
    }
  }
}

// ==========================================
// 3. Tab Switching
// ==========================================

function switchTab(tabId) {
  document.querySelectorAll(".tab-content").forEach(el => el.classList.remove("active"));
  document.querySelectorAll(".nav-tab").forEach(el => el.classList.remove("active"));

  const targetTab = document.getElementById(tabId);
  if (targetTab) targetTab.classList.add("active");

  const btn = document.querySelector(`[data-tab="${tabId}"]`);
  if (btn) btn.classList.add("active");

  if (tabId === "ordersTab") renderOrders();
  if (tabId === "adminTab") {
    renderAdminOrders();
    renderAdminCustomers();
  }
}

// ==========================================
// 4. Product Catalog & Requirement 3 Queries
// ==========================================

function onQueryModeChange() {
  const mode = document.getElementById("queryModeSelect").value;
  const idGroup = document.getElementById("inputIdGroup");
  const nameGroup = document.getElementById("inputNameGroup");
  const companyGroup = document.getElementById("inputCompanyGroup");
  const priceGroup = document.getElementById("inputPriceGroup");
  const minPriceGroup = document.getElementById("inputMinPriceGroup");
  const maxPriceGroup = document.getElementById("inputMaxPriceGroup");

  [idGroup, nameGroup, companyGroup, priceGroup, minPriceGroup, maxPriceGroup].forEach(el => el.classList.add("hidden"));

  switch (mode) {
    case "all":
      nameGroup.classList.remove("hidden");
      break;
    case "mode_i": // Product ID
      idGroup.classList.remove("hidden");
      break;
    case "mode_ii": // Product Name
      nameGroup.classList.remove("hidden");
      break;
    case "mode_iii": // Name and Exact Price
      nameGroup.classList.remove("hidden");
      priceGroup.classList.remove("hidden");
      break;
    case "mode_iv": // Name and Price Range
      nameGroup.classList.remove("hidden");
      minPriceGroup.classList.remove("hidden");
      maxPriceGroup.classList.remove("hidden");
      break;
    case "mode_v": // Name and Company
      nameGroup.classList.remove("hidden");
      companyGroup.classList.remove("hidden");
      break;
    case "mode_vi": // Name, Company and Price
      nameGroup.classList.remove("hidden");
      companyGroup.classList.remove("hidden");
      priceGroup.classList.remove("hidden");
      break;
  }
  applyCatalogSearch();
}

function applyCatalogSearch() {
  const mode = document.getElementById("queryModeSelect").value;
  const nameVal = document.getElementById("queryName").value.trim().toLowerCase();
  const idVal = document.getElementById("queryId").value.trim().toLowerCase();
  const companyVal = document.getElementById("queryCompany").value.trim().toLowerCase();
  const priceVal = parseFloat(document.getElementById("queryPrice").value);
  const minVal = parseFloat(document.getElementById("queryMinPrice").value);
  const maxVal = parseFloat(document.getElementById("queryMaxPrice").value);

  let filtered = state.products.filter(p => {
    if (state.activeCompanyFilter !== "All" && p.companyName.toLowerCase() !== state.activeCompanyFilter.toLowerCase()) {
      return false;
    }

    switch (mode) {
      case "all":
        return !nameVal || p.name.toLowerCase().includes(nameVal) || p.companyName.toLowerCase().includes(nameVal);

      case "mode_i": // Query (i): By ID
        return !idVal || p.productId.toLowerCase() === idVal;

      case "mode_ii": // Query (ii): By Name
        return !nameVal || p.name.toLowerCase().includes(nameVal);

      case "mode_iii": // Query (iii): By Name and Price
        const nameMatch3 = !nameVal || p.name.toLowerCase().includes(nameVal);
        const priceMatch3 = isNaN(priceVal) || Math.abs(p.price - priceVal) < 1.0;
        return nameMatch3 && priceMatch3;

      case "mode_iv": // Query (iv): By Name and Price Range
        const nameMatch4 = !nameVal || p.name.toLowerCase().includes(nameVal);
        const minOk = isNaN(minVal) || p.price >= minVal;
        const maxOk = isNaN(maxVal) || p.price <= maxVal;
        return nameMatch4 && minOk && maxOk;

      case "mode_v": // Query (v): By Name and Company
        const nameMatch5 = !nameVal || p.name.toLowerCase().includes(nameVal);
        const compMatch5 = !companyVal || p.companyName.toLowerCase().includes(companyVal);
        return nameMatch5 && compMatch5;

      case "mode_vi": // Query (vi): By Name, Company, and Price
        const nameMatch6 = !nameVal || p.name.toLowerCase().includes(nameVal);
        const compMatch6 = !companyVal || p.companyName.toLowerCase().includes(companyVal);
        const priceMatch6 = isNaN(priceVal) || Math.abs(p.price - priceVal) < 1.0;
        return nameMatch6 && compMatch6 && priceMatch6;

      default:
        return true;
    }
  });

  renderCatalog(filtered);
}

function resetFilters() {
  document.getElementById("queryName").value = "";
  document.getElementById("queryId").value = "";
  document.getElementById("queryCompany").value = "";
  document.getElementById("queryPrice").value = "";
  document.getElementById("queryMinPrice").value = "";
  document.getElementById("queryMaxPrice").value = "";
  document.getElementById("queryModeSelect").value = "all";
  state.activeCompanyFilter = "All";

  document.querySelectorAll(".chip").forEach(c => c.classList.remove("active"));
  const allChip = document.querySelector(".chip:first-child");
  if (allChip) allChip.classList.add("active");

  onQueryModeChange();
}

function filterByCompanyChip(company, chipEl) {
  state.activeCompanyFilter = company;
  document.querySelectorAll(".chip").forEach(c => c.classList.remove("active"));
  chipEl.classList.add("active");
  applyCatalogSearch();
}

function renderCatalog(productsToRender = state.products) {
  const container = document.getElementById("productsGrid");
  const countEl = document.getElementById("resultsCount");
  countEl.textContent = `Showing ${productsToRender.length} Product${productsToRender.length === 1 ? "" : "s"}`;

  if (productsToRender.length === 0) {
    container.innerHTML = `
      <div style="grid-column: 1/-1; text-align: center; padding: 60px 20px; color: #64748b;">
        <div style="font-size: 36px; margin-bottom: 8px;">🔍</div>
        <h4>No products found matching query</h4>
        <p style="font-size: 13px;">Try adjusting your search criteria, brand filter, or price range.</p>
        <button class="btn btn-secondary" style="margin-top: 12px;" onclick="resetFilters()">Reset All Filters</button>
      </div>
    `;
    return;
  }

  const cust = getCurrentCustomer();
  const userCity = cust ? cust.city : "Delhi";

  container.innerHTML = productsToRender.map(p => {
    const isLowStock = p.stockQuantity > 0 && p.stockQuantity <= 10;
    const isOutOfStock = p.stockQuantity <= 0;
    const discountPercent = p.mrp ? Math.round(((p.mrp - p.price) / p.mrp) * 100) : 0;

    return `
      <div class="product-card">
        ${p.badge ? `<div class="product-badge-ribbon">${p.badge}</div>` : ""}
        <div>
          <div class="product-card-top">
            <span class="product-id-badge">${p.productId}</span>
            <span class="product-company">${escapeHtml(p.companyName)}</span>
          </div>
          <h4 class="product-title">${escapeHtml(p.name)}</h4>
          <div class="product-rating">
            <span class="stars">★★★★★</span>
            <strong style="color: #f1f5f9;">${p.rating || "4.8"}</strong>
            <span class="rating-count">(${Number(p.reviews || 1200).toLocaleString('en-IN')})</span>
          </div>
          <p class="product-desc">${escapeHtml(p.description)}</p>
          <div class="product-delivery-eta">
            <span>⚡</span>
            <span>Get it by <strong>Tomorrow</strong> in ${escapeHtml(userCity)}</span>
          </div>
        </div>
        <div class="product-card-bottom">
          <div class="product-price-block">
            <div class="price-row">
              <span class="price-val">${formatINR(p.price)}</span>
              ${p.mrp ? `<span class="price-mrp">${formatINR(p.mrp)}</span>` : ""}
              ${discountPercent > 0 ? `<span class="discount-badge">${discountPercent}% off</span>` : ""}
            </div>
            <span class="tax-note">Inclusive of all GST taxes</span>
            <span class="stock-tag ${isOutOfStock ? 'text-danger' : isLowStock ? 'low-stock' : 'in-stock'}">
              ${isOutOfStock ? 'Out of stock' : `${p.stockQuantity} in stock at Delhi Hub`}
            </span>
          </div>
          <button class="btn-add-cart" ${isOutOfStock ? 'disabled style="opacity: 0.5; cursor: not-allowed;"' : ''} onclick="addToCart('${p.productId}')">
            <span>+</span> Add to Cart
          </button>
        </div>
      </div>
    `;
  }).join("");
}

// ==========================================
// 5. Shopping Basket & Pricing Calculation (Req 5, 9, 11, 12 in INR)
// ==========================================

function getActiveCart() {
  const cust = getCurrentCustomer();
  if (!cust) return [];
  if (!state.carts[cust.id]) {
    state.carts[cust.id] = [];
  }
  return state.carts[cust.id];
}

function addToCart(productId) {
  if (state.currentSession === "admin") {
    showToast("Please switch to a customer account to add items to basket", "⚠️");
    return;
  }

  const product = state.products.find(p => p.productId === productId);
  if (!product) return;

  const cart = getActiveCart();
  const existing = cart.find(item => item.productId === productId);

  const currentQtyInCart = existing ? existing.quantity : 0;
  if (currentQtyInCart + 1 > product.stockQuantity) {
    showToast(`Only ${product.stockQuantity} available in stock for ${product.name}!`, "⚠️");
    return;
  }

  if (existing) {
    existing.quantity++;
  } else {
    cart.push({
      productId: product.productId,
      name: product.name,
      companyName: product.companyName,
      price: product.price,
      quantity: 1
    });
  }

  updateCartUI();
  showToast(`Added ${product.name} to cart!`, "🛒");
}

function updateCartQty(productId, delta) {
  const cart = getActiveCart();
  const item = cart.find(i => i.productId === productId);
  if (!item) return;

  const product = state.products.find(p => p.productId === productId);
  const newQty = item.quantity + delta;

  if (newQty <= 0) {
    removeFromCart(productId);
    return;
  }

  if (product && newQty > product.stockQuantity) {
    showToast(`Only ${product.stockQuantity} units available!`, "⚠️");
    return;
  }

  item.quantity = newQty;
  updateCartUI();
}

function removeFromCart(productId) {
  const cust = getCurrentCustomer();
  if (!cust) return;
  state.carts[cust.id] = state.carts[cust.id].filter(i => i.productId !== productId);
  updateCartUI();
}

function updateCartUI() {
  const cart = getActiveCart();
  const cust = getCurrentCustomer();
  const totalCount = cart.reduce((sum, item) => sum + item.quantity, 0);

  document.getElementById("cartCount").textContent = totalCount;

  // Calculate pricing breakdown in INR
  const subtotal = cart.reduce((sum, item) => sum + (item.price * item.quantity), 0);

  // Requirement 12: Customer History Threshold Discount (15% if >= 2 past orders >= ₹5,000)
  const custPastOrders = cust ? state.orders.filter(o => o.customerId === cust.id && o.status !== "CANCELLED") : [];
  const qualifyingCount = custPastOrders.filter(o => o.orderAmount >= 5000.0).length;

  let discount = 0;
  const discountLine = document.getElementById("discountLineRow");
  if (qualifyingCount >= 2 && subtotal > 0) {
    discount = subtotal * 0.15; // 15% loyalty discount
    discountLine.style.display = "flex";
    document.getElementById("summaryDiscount").textContent = `-${formatINR(discount)} (15% Loyalty Reward)`;
  } else {
    discountLine.style.display = "none";
  }

  // Requirement 11 & 9: Shipping is WAIVED (₹0) if active BharatPrime subscription, else ₹99.00
  const isMember = cust && cust.subscription !== null;
  const shipping = isMember || subtotal === 0 ? 0.0 : 99.00;

  const waiverNote = document.getElementById("shippingWaiverNote");
  if (isMember) {
    waiverNote.innerHTML = `⭐ <strong>BharatPrime Free Express Delivery Applied!</strong> (₹99.00 saved)`;
    waiverNote.style.color = "#6ee7b7";
    waiverNote.style.background = "rgba(16, 185, 129, 0.1)";
    waiverNote.style.borderColor = "rgba(16, 185, 129, 0.3)";
  } else {
    waiverNote.innerHTML = `💡 Join BharatPrime VIP to get 100% Free Express Delivery on this order!`;
    waiverNote.style.color = "#fbbf24";
    waiverNote.style.background = "rgba(245, 158, 11, 0.1)";
    waiverNote.style.borderColor = "rgba(245, 158, 11, 0.25)";
  }

  const finalTotal = Math.max(0, (subtotal - discount) + shipping);

  document.getElementById("summarySubtotal").textContent = formatINR(subtotal);
  document.getElementById("summaryShipping").textContent = shipping === 0 ? "₹0.00 (FREE)" : formatINR(shipping);
  document.getElementById("summaryTotal").textContent = formatINR(finalTotal);

  // Render items list
  const container = document.getElementById("cartItemsList");
  if (cart.length === 0) {
    container.innerHTML = `
      <div class="empty-cart-msg">
        <div style="font-size: 36px; margin-bottom: 8px;">🛒</div>
        <p style="font-weight: 700; color: #fff;">Your shopping cart is empty</p>
        <span style="font-size: 12px; color: #64748b;">Explore smartphones, audio, and laptops to add products</span>
      </div>
    `;
    document.getElementById("checkoutBtn").disabled = true;
    document.getElementById("checkoutBtn").style.opacity = "0.5";
  } else {
    document.getElementById("checkoutBtn").disabled = false;
    document.getElementById("checkoutBtn").style.opacity = "1";
    container.innerHTML = cart.map(item => `
      <div class="cart-item-card">
        <div class="cart-item-info">
          <h5>${escapeHtml(item.name)}</h5>
          <span>${formatINR(item.price)} • ${escapeHtml(item.companyName)}</span>
        </div>
        <div class="cart-item-qty">
          <button class="qty-btn" onclick="updateCartQty('${item.productId}', -1)">-</button>
          <span class="qty-val">${item.quantity}</span>
          <button class="qty-btn" onclick="updateCartQty('${item.productId}', 1)">+</button>
        </div>
        <div class="cart-item-total">${formatINR(item.price * item.quantity)}</div>
      </div>
    `).join("");
  }
}

function toggleCartDrawer() {
  const drawer = document.getElementById("cartDrawer");
  drawer.classList.toggle("open");
}

// ==========================================
// 6. Checkout & Polymorphic Payment (Req 7 & 10)
// ==========================================

function openCheckoutModal() {
  const cart = getActiveCart();
  if (cart.length === 0) return;

  toggleCartDrawer(); // Close drawer
  const total = document.getElementById("summaryTotal").textContent;
  document.getElementById("modalPayableAmount").textContent = total;

  updateGiftCardUI();

  document.getElementById("checkoutModal").classList.add("open");
}

function closeCheckoutModal() {
  document.getElementById("checkoutModal").classList.remove("open");
}

function selectPaymentMethod(method, btn) {
  state.selectedPaymentMethod = method;
  document.querySelectorAll(".payment-tab").forEach(t => t.classList.remove("active"));
  btn.classList.add("active");

  document.querySelectorAll(".payment-form").forEach(f => f.classList.remove("active"));
  const formMap = {
    upi: "formUpi",
    card: "formCard",
    cod: "formCod",
    giftcard: "formGiftCard"
  };
  document.getElementById(formMap[method]).classList.add("active");
}

function useQuickGc(code, pin) {
  document.getElementById("gcCodeInput").value = code;
  document.getElementById("gcPinInput").value = pin;
  updateGiftCardUI();
}

function updateGiftCardUI() {
  const code = document.getElementById("gcCodeInput").value.trim().toUpperCase();
  const gc = state.giftCards.find(g => g.code === code);
  const statusEl = document.getElementById("gcBalanceStatus");

  if (gc) {
    statusEl.innerHTML = `✓ Verified Gift Voucher: <strong>${formatINR(gc.balance)} Available Balance</strong>`;
    statusEl.style.color = "#6ee7b7";
  } else {
    statusEl.innerHTML = `Enter a valid Indian gift card code (e.g. DIWALI500, FESTIVE2000, MEGA5000)`;
    statusEl.style.color = "#94a3b8";
  }
}

document.getElementById("gcCodeInput")?.addEventListener("input", updateGiftCardUI);

function executeCheckout() {
  const cust = getCurrentCustomer();
  const cart = getActiveCart();
  if (!cust || cart.length === 0) return;

  const subtotal = cart.reduce((sum, item) => sum + (item.price * item.quantity), 0);
  const isMember = cust.subscription !== null;
  const shipping = isMember ? 0.0 : 99.00;

  // Discount calculation in INR (Req 12)
  const custPastOrders = state.orders.filter(o => o.customerId === cust.id && o.status !== "CANCELLED");
  const qualifyingCount = custPastOrders.filter(o => o.orderAmount >= 5000.0).length;
  const discount = qualifyingCount >= 2 ? (subtotal * 0.15) : 0.0;
  const finalTotal = Math.max(0, (subtotal - discount) + shipping);

  let paymentResult = null;
  const method = state.selectedPaymentMethod;

  // Polymorphic Payment Execution
  if (method === "upi") {
    const upiId = document.getElementById("upiIdInput").value.trim();
    const app = document.getElementById("upiAppInput").value;
    if (!upiId || !upiId.includes("@")) {
      showToast("Please enter a valid UPI ID (e.g. name@oksbi or name@ybl)", "⚠️");
      return;
    }
    paymentResult = {
      type: `UPI (${app})`,
      txId: "TXN-UPI-" + Math.random().toString(36).substring(2, 10).toUpperCase(),
      status: "SUCCESS",
      details: `${upiId}`
    };
  } else if (method === "card") {
    const cardNo = document.getElementById("cardNumberInput").value.replace(/\s+/g, "");
    const cardHolder = document.getElementById("cardHolderInput").value.trim();
    const cardType = document.getElementById("cardTypeInput").value;
    if (cardNo.length < 12) {
      showToast("Please enter a valid 16-digit card number", "⚠️");
      return;
    }
    paymentResult = {
      type: cardType === "CREDIT_CARD" ? "Credit Card (Visa/Mastercard)" : "RuPay Debit Card",
      txId: "TXN-CARD-" + Math.random().toString(36).substring(2, 10).toUpperCase(),
      status: "SUCCESS",
      details: `****-****-****-${cardNo.slice(-4)} (${cardHolder})`
    };
  } else if (method === "cod") {
    const pin = Math.floor(1000 + Math.random() * 9000);
    const instructions = document.getElementById("codInstructionsInput").value.trim();
    paymentResult = {
      type: "Cash on Delivery (COD)",
      txId: "COD-" + Math.random().toString(36).substring(2, 9).toUpperCase(),
      status: "PENDING_ON_DELIVERY",
      details: `Doorstep OTP / PIN: ${pin} | ${instructions}`
    };
  } else if (method === "giftcard") {
    const code = document.getElementById("gcCodeInput").value.trim().toUpperCase();
    const pin = document.getElementById("gcPinInput").value.trim();
    const gc = state.giftCards.find(g => g.code === code && g.pin === pin);

    if (!gc) {
      showToast("Invalid Gift Card Code or PIN!", "❌");
      return;
    }
    if (gc.balance < finalTotal) {
      showToast(`Insufficient Gift Voucher balance! (Available: ${formatINR(gc.balance)}, Required: ${formatINR(finalTotal)})`, "❌");
      return;
    }

    gc.balance -= finalTotal;
    paymentResult = {
      type: "Festive Gift Voucher",
      txId: "TXN-GC-" + Math.random().toString(36).substring(2, 9).toUpperCase(),
      status: "SUCCESS",
      details: `Voucher: ****-${code.slice(-4)} | Remaining Balance: ${formatINR(gc.balance)}`
    };
    renderActiveGiftCards();
  }

  // Deduct inventory stock
  for (const item of cart) {
    const p = state.products.find(prod => prod.productId === item.productId);
    if (p) p.stockQuantity -= item.quantity;
  }

  // Create Order with initial status: CREATED_AND_RECEIVED (Requirement 5)
  const orderId = "ORD-DEL-" + Math.floor(100000 + Math.random() * 900000);
  const now = new Date();
  const order = {
    orderId: orderId,
    customerId: cust.id,
    customerName: cust.name,
    customerEmail: cust.email,
    customerPhone: cust.phone,
    shippingAddress: cust.address,
    date: now.toLocaleString('en-IN', { dateStyle: 'medium', timeStyle: 'short' }),
    items: [...cart],
    orderAmount: subtotal,
    discountAmount: discount,
    shippingCharge: shipping,
    totalAmount: finalTotal,
    status: "CREATED_AND_RECEIVED",
    payment: paymentResult,
    trackingInfo: null
  };

  state.orders.unshift(order);

  // Clear cart
  state.carts[cust.id] = [];
  updateCartUI();
  renderCatalog();
  closeCheckoutModal();

  showToast(`Order ${orderId} placed successfully!`, "🎉");

  // Show GST Tax Invoice Modal
  openInvoiceModal(order);
}

// ==========================================
// 7. Order Status Intervals & Tracking (Req 5 & 6)
// ==========================================

const STATUS_LABELS = {
  CREATED_AND_RECEIVED: "Order Just Created and Received",
  DISPATCHED: "Order Dispatched with Delhivery",
  IN_TRANSIT: "Order With Courier Agent and In Transit",
  DELIVERED: "Order Delivered to Doorstep",
  RETURNED_AND_RECEIVED: "Order Returned by Customer and Received"
};

function renderOrders() {
  const cust = getCurrentCustomer();
  const list = document.getElementById("ordersList");
  const badge = document.getElementById("orderCountBadge");

  const myOrders = state.orders.filter(o => o.customerId === cust.id);
  badge.textContent = myOrders.length;

  if (myOrders.length === 0) {
    list.innerHTML = `
      <div style="text-align: center; padding: 60px 20px; color: #64748b;">
        <div style="font-size: 36px; margin-bottom: 8px;">📦</div>
        <h4>No past orders placed</h4>
        <p style="font-size: 13px;">Browse smartphones, audio, and laptops in the catalog to place an order!</p>
      </div>
    `;
    return;
  }

  list.innerHTML = myOrders.map(o => {
    const statusClass = {
      CREATED_AND_RECEIVED: "created",
      DISPATCHED: "dispatched",
      IN_TRANSIT: "in-transit",
      DELIVERED: "delivered",
      RETURNED_AND_RECEIVED: "returned"
    }[o.status] || "created";

    const itemsSummary = o.items.map(i => `${i.name} (x${i.quantity})`).join(", ");

    return `
      <div class="order-card">
        <div class="order-card-header">
          <div class="order-meta">
            <h4>${o.orderId}</h4>
            <span>Placed on ${o.date} • ${o.payment.type}</span>
          </div>
          <span class="status-badge ${statusClass}">${STATUS_LABELS[o.status]}</span>
        </div>
        <div class="order-items-snippet">
          <strong>Items Ordered:</strong> ${escapeHtml(itemsSummary)}
        </div>
        <div class="order-actions">
          <div class="order-total-display">${formatINR(o.totalAmount)}</div>
          <div style="display: flex; gap: 8px;">
            <button class="btn btn-secondary" onclick="viewOrderInvoice('${o.orderId}')">🧾 GST Tax Invoice</button>
            <button class="btn btn-primary" onclick="viewOrderTracking('${o.orderId}')">🚚 Track Shipment</button>
          </div>
        </div>
      </div>
    `;
  }).join("");
}

function viewOrderInvoice(orderId) {
  const order = state.orders.find(o => o.orderId === orderId);
  if (order) openInvoiceModal(order);
}

function openInvoiceModal(order) {
  const body = document.getElementById("invoiceModalBody");
  const cgst = (order.orderAmount * 0.09);
  const sgst = (order.orderAmount * 0.09);

  body.innerHTML = `
    <div style="font-family: var(--font-mono); font-size: 12px; background: #0b1122; padding: 22px; border-radius: 12px; border: 1px solid #243152; line-height: 1.6;">
      <div style="display: flex; justify-content: space-between; border-bottom: 2px solid #34446e; padding-bottom: 12px; margin-bottom: 12px;">
        <div>
          <strong style="font-size: 16px; color: #fdba74;">BharatMart Retail India Pvt. Ltd.</strong><br>
          <span style="color: #94a3b8; font-size: 11px;">Okhla Industrial Area Phase-III, New Delhi - 110020</span><br>
          <strong>GSTIN:</strong> 07AABCB1234F1Z9 (State Code: 07 - Delhi)
        </div>
        <div style="text-align: right; color: #94a3b8; font-size: 11px;">
          <strong>TAX INVOICE</strong><br>
          Invoice No: <strong>INV-${order.orderId}</strong><br>
          Date: ${order.date}<br>
          Status: <span style="color: #6ee7b7; font-weight: bold;">${STATUS_LABELS[order.status]}</span>
        </div>
      </div>

      <div style="margin-bottom: 14px; font-size: 12px; color: #cbd5e1; background: #131c31; padding: 10px; border-radius: 6px;">
        <strong>Billed &amp; Shipped To:</strong> ${escapeHtml(order.customerName)} (${escapeHtml(order.customerEmail)}) | Phone: ${escapeHtml(order.customerPhone)}<br>
        <strong>Delivery Address:</strong> ${escapeHtml(order.shippingAddress.street)}, ${escapeHtml(order.shippingAddress.city)}, ${escapeHtml(order.shippingAddress.state)} - ${escapeHtml(order.shippingAddress.zipCode)}, ${escapeHtml(order.shippingAddress.country)}<br>
        <strong>Payment Method:</strong> ${order.payment.type} (Txn Ref: <code>${order.payment.txId}</code>)
      </div>

      <table style="width: 100%; border-collapse: collapse; margin-bottom: 14px; font-size: 11px;">
        <thead>
          <tr style="border-bottom: 1px solid #34446e; color: #94a3b8; background: #131c31;">
            <th style="text-align: left; padding: 6px;">Item Description</th>
            <th style="text-align: center; padding: 6px;">HSN</th>
            <th style="text-align: right; padding: 6px;">Qty</th>
            <th style="text-align: right; padding: 6px;">Unit Price (₹)</th>
            <th style="text-align: right; padding: 6px;">Total (₹)</th>
          </tr>
        </thead>
        <tbody>
          ${order.items.map(i => `
            <tr style="border-bottom: 1px dashed #243152;">
              <td style="padding: 6px;">${escapeHtml(i.name)} <span style="color: #64748b;">(${escapeHtml(i.companyName)})</span></td>
              <td style="text-align: center; padding: 6px; color: #94a3b8;">8517</td>
              <td style="text-align: right; padding: 6px;">${i.quantity}</td>
              <td style="text-align: right; padding: 6px;">${formatINR(i.price)}</td>
              <td style="text-align: right; padding: 6px; font-weight: bold;">${formatINR(i.price * i.quantity)}</td>
            </tr>
          `).join("")}
        </tbody>
      </table>

      <div style="border-top: 1px solid #34446e; padding-top: 10px; display: flex; flex-direction: column; gap: 4px;">
        <div style="display: flex; justify-content: space-between;">
          <span>Taxable Amount:</span>
          <span>${formatINR(order.orderAmount)}</span>
        </div>
        <div style="display: flex; justify-content: space-between; color: #94a3b8; font-size: 11px;">
          <span>CGST (9.0%):</span>
          <span>${formatINR(cgst)}</span>
        </div>
        <div style="display: flex; justify-content: space-between; color: #94a3b8; font-size: 11px;">
          <span>SGST / UTGST (9.0%):</span>
          <span>${formatINR(sgst)}</span>
        </div>
        ${order.discountAmount > 0 ? `
          <div style="display: flex; justify-content: space-between; color: #34d399; font-weight: bold;">
            <span>Festive / Loyalty Reward Discount (Req 12):</span>
            <span>-${formatINR(order.discountAmount)}</span>
          </div>
        ` : ""}
        <div style="display: flex; justify-content: space-between;">
          <span>Standard Delivery Charges:</span>
          <span>${order.shippingCharge === 0 ? "₹0.00 (WAIVED - BharatPrime Free Shipping)" : formatINR(order.shippingCharge)}</span>
        </div>
        <div style="display: flex; justify-content: space-between; font-size: 15px; font-weight: 800; color: #fdba74; border-top: 2px solid #34446e; padding-top: 8px; margin-top: 6px;">
          <span>TOTAL AMOUNT PAID (INCL. GST):</span>
          <span>${formatINR(order.totalAmount)}</span>
        </div>
      </div>
      <div style="margin-top: 16px; border-top: 1px dashed #243152; padding-top: 8px; font-size: 10px; color: #64748b; text-align: center;">
        This is a computer-generated tax invoice under Central Goods and Services Tax Act, 2017. No signature required.
      </div>
    </div>
  `;
  document.getElementById("invoiceModal").classList.add("open");
}

function closeInvoiceModal() {
  document.getElementById("invoiceModal").classList.remove("open");
}

function printInvoice() {
  window.print();
}

// Live Courier Tracking (Requirement 6 - Delhivery Express)
function viewOrderTracking(orderId) {
  const order = state.orders.find(o => o.orderId === orderId);
  if (!order) return;

  const body = document.getElementById("trackingModalBody");

  if (!order.trackingInfo) {
    body.innerHTML = `
      <div style="text-align: center; padding: 30px 20px;">
        <div style="font-size: 40px; margin-bottom: 10px;">📦</div>
        <h4>Order Being Prepared at Delhi Fulfillment Center</h4>
        <p style="font-size: 13px; color: #94a3b8; margin-top: 6px;">
          Current Status: <strong>${STATUS_LABELS[order.status]}</strong>.<br>
          Shipment tracking will be activated once dispatched by the Delhi Hub Operations team.
        </p>
      </div>
    `;
  } else {
    const t = order.trackingInfo;
    body.innerHTML = `
      <div class="tracking-report-card">
        <div class="tracking-meta-grid">
          <div>
            <span>AWB Tracking Number</span>
            <strong style="font-family: var(--font-mono); color: #fdba74;">${t.trackingNumber}</strong>
          </div>
          <div>
            <span>Logistics Partner</span>
            <strong>${escapeHtml(t.courierPartner)}</strong>
          </div>
          <div>
            <span>Shipment Status</span>
            <strong style="color: #6ee7b7;">${STATUS_LABELS[t.currentStatus]}</strong>
          </div>
          <div>
            <span>Estimated Delivery</span>
            <strong>${t.estimatedEta}</strong>
          </div>
        </div>

        <h5 style="margin-bottom: 14px; font-size: 12px; font-weight: 800; text-transform: uppercase; color: #94a3b8; letter-spacing: 0.5px;">Live Delhi NCR Milestones</h5>
        <div class="timeline">
          ${t.checkpoints.map(cp => `
            <div class="timeline-step">
              <div class="step-time">${cp.time}</div>
              <div class="step-location">📍 ${escapeHtml(cp.location)}</div>
              <div class="step-message">${escapeHtml(cp.message)}</div>
            </div>
          `).join("")}
        </div>
      </div>
    `;
  }

  document.getElementById("trackingModal").classList.add("open");
}

function closeTrackingModal() {
  document.getElementById("trackingModal").classList.remove("open");
}

// ==========================================
// 8. Pincode Speed Checker Modal
// ==========================================

function openPinModal() {
  document.getElementById("modalPinInput").value = state.currentPincode;
  checkPincodeSpeed();
  document.getElementById("pinModal").classList.add("open");
}

function closePinModal() {
  document.getElementById("pinModal").classList.remove("open");
}

function checkPincodeSpeed() {
  const pin = (document.getElementById("modalPinInput")?.value || "").trim();
  const res = document.getElementById("pinResultBox");
  if (!res) return;

  if (pin.length !== 6 || isNaN(pin)) {
    res.innerHTML = `<span style="color: #f59e0b;">Please enter a valid 6-digit Indian PIN code.</span>`;
    return;
  }

  const match = PIN_DATABASE[pin];
  if (match) {
    res.innerHTML = `
      <div style="color: #6ee7b7; font-weight: 800;">✓ Delivery Available to ${match.city}, ${match.state}</div>
      <div style="margin-top: 4px; color: #cbd5e1;">⚡ Expected Delivery: <strong>${match.eta}</strong> via Delhivery Express Hub</div>
      <div style="font-size: 11px; color: #94a3b8; margin-top: 2px;">Eligible for Cash on Delivery (COD) and 100% Free Shipping with BharatPrime.</div>
    `;
  } else {
    res.innerHTML = `
      <div style="color: #38bdf8; font-weight: 700;">✓ Standard Pan-India Coverage for PIN ${pin}</div>
      <div style="margin-top: 4px; color: #cbd5e1;">Expected delivery in <strong>2-3 business days</strong> via Blue Dart / Speed Post.</div>
    `;
  }
}

function applyCustomPincode() {
  const pin = document.getElementById("modalPinInput").value.trim();
  if (pin.length === 6 && !isNaN(pin)) {
    state.currentPincode = pin;
    const match = PIN_DATABASE[pin];
    const locName = match ? `${match.city} ${pin}` : `India (${pin})`;
    document.getElementById("navLocMain").textContent = locName;
    closePinModal();
    showToast(`Delivery location set to ${locName}`, "📍");
    renderCatalog();
  } else {
    showToast("Please enter a valid 6-digit Indian PIN code", "⚠️");
  }
}

// ==========================================
// 9. Membership Subscriptions (Requirement 11 in INR)
// ==========================================

function purchaseSubscription(tier, price) {
  const cust = getCurrentCustomer();
  if (state.currentSession === "admin" || !cust) {
    showToast("Please switch to a customer account to purchase subscriptions", "⚠️");
    return;
  }

  const daysMap = {
    MONTHLY: 30,
    QUARTERLY: 90,
    HALF_YEARLY: 180,
    ANNUALLY: 365
  };

  const expDate = new Date();
  expDate.setDate(expDate.getDate() + daysMap[tier]);

  cust.subscription = {
    tier: tier,
    price: price,
    expires: expDate.toLocaleDateString('en-IN', { day: 'numeric', month: 'short', year: 'numeric' })
  };

  updateSessionUI();
  updateCartUI();
  showToast(`Subscribed to ${tier} Membership! You now enjoy ₹0 Free Express Delivery on all orders!`, "🌟");
}

// ==========================================
// 10. Admin Console Handlers (Req 2, 3, 5 - Delhi Hub)
// ==========================================

function renderAdminOrders() {
  const tbody = document.getElementById("adminOrdersTableBody");
  if (!tbody) return;

  if (state.orders.length === 0) {
    tbody.innerHTML = `<tr><td colspan="6" style="text-align: center; color: #64748b; padding: 24px;">No platform orders placed yet.</td></tr>`;
    return;
  }

  tbody.innerHTML = state.orders.map(o => {
    let nextActionBtn = "";
    if (o.status === "CREATED_AND_RECEIVED") {
      nextActionBtn = `<button class="btn btn-secondary" style="font-size: 11px; padding: 4px 10px;" onclick="adminProgressOrder('${o.orderId}', 'DISPATCHED')">Dispatch with Delhivery</button>`;
    } else if (o.status === "DISPATCHED") {
      nextActionBtn = `<button class="btn btn-secondary" style="font-size: 11px; padding: 4px 10px;" onclick="adminProgressOrder('${o.orderId}', 'IN_TRANSIT')">Move to Local Hub</button>`;
    } else if (o.status === "IN_TRANSIT") {
      nextActionBtn = `<button class="btn btn-primary" style="font-size: 11px; padding: 4px 10px;" onclick="adminProgressOrder('${o.orderId}', 'DELIVERED')">Mark Delivered</button>`;
    } else if (o.status === "DELIVERED") {
      nextActionBtn = `<button class="btn btn-outline" style="font-size: 11px; padding: 4px 10px;" onclick="adminProgressOrder('${o.orderId}', 'RETURNED_AND_RECEIVED')">Return & Restock</button>`;
    } else {
      nextActionBtn = `<span style="font-size: 11px; color: #94a3b8;">Restocked in Warehouse</span>`;
    }

    return `
      <tr>
        <td style="font-family: var(--font-mono); font-weight: 700; color: #fdba74;">${o.orderId}</td>
        <td>${escapeHtml(o.customerName)} (${o.shippingAddress.city})</td>
        <td><strong>${formatINR(o.totalAmount)}</strong></td>
        <td><span class="status-badge" style="font-size: 11px;">${STATUS_LABELS[o.status]}</span></td>
        <td>${o.trackingInfo ? `${o.trackingInfo.courierPartner} (${o.trackingInfo.currentHub})` : "Pending Dispatch"}</td>
        <td>${nextActionBtn}</td>
      </tr>
    `;
  }).join("");
}

function adminProgressOrder(orderId, targetStatus) {
  const order = state.orders.find(o => o.orderId === orderId);
  if (!order) return;

  const now = new Date().toLocaleString('en-IN', { hour: '2-digit', minute: '2-digit', day: 'numeric', month: 'short' });

  if (targetStatus === "DISPATCHED") {
    order.status = "DISPATCHED";
    order.trackingInfo = {
      trackingNumber: "DEL" + Math.floor(100000000 + Math.random() * 900000000) + "IN",
      courierPartner: "Delhivery Express",
      currentStatus: "DISPATCHED",
      currentHub: "Delhi Central Cargo Hub, Palam",
      estimatedEta: "Tomorrow by 11:00 AM",
      checkpoints: [
        { time: now, location: "Delhi Central Cargo Hub, Palam", message: "Package picked up by Delhivery Logistics executive" }
      ]
    };
    showToast(`Order ${orderId} dispatched via Delhivery Express!`, "🚚");
  } else if (targetStatus === "IN_TRANSIT") {
    order.status = "IN_TRANSIT";
    order.trackingInfo.currentStatus = "IN_TRANSIT";
    order.trackingInfo.currentHub = "Okhla Local Delivery Hub, New Delhi";
    order.trackingInfo.checkpoints.push({
      time: now,
      location: "Okhla Local Delivery Hub, New Delhi",
      message: "Package arrived at local delivery hub and is out for doorstep delivery"
    });
    showToast(`Order ${orderId} reached Okhla Hub and is in transit.`, "📦");
  } else if (targetStatus === "DELIVERED") {
    order.status = "DELIVERED";
    order.trackingInfo.currentStatus = "DELIVERED";
    order.trackingInfo.currentHub = order.shippingAddress.city;
    order.trackingInfo.checkpoints.push({
      time: now,
      location: `${order.shippingAddress.street}, ${order.shippingAddress.city}`,
      message: `Package handed directly to customer ${order.customerName}`
    });
    showToast(`Order ${orderId} successfully marked as DELIVERED!`, "✅");
  } else if (targetStatus === "RETURNED_AND_RECEIVED") {
    order.status = "RETURNED_AND_RECEIVED";
    order.trackingInfo.currentStatus = "RETURNED_AND_RECEIVED";
    order.trackingInfo.currentHub = "Delhi Returns Warehouse (Okhla Phase-III)";
    order.trackingInfo.checkpoints.push({
      time: now,
      location: "Delhi Returns Warehouse (Okhla Phase-III)",
      message: "Customer return inspected and units successfully restocked into active catalog"
    });

    // Requirement 5 Restocking:
    for (const item of order.items) {
      const prod = state.products.find(p => p.productId === item.productId);
      if (prod) prod.stockQuantity += item.quantity;
    }
    renderCatalog();
    showToast(`Order ${orderId} returned. Items automatically restocked into catalog!`, "🔄");
  }

  renderAdminOrders();
  renderOrders();
}

function handleAdminAddProduct(e) {
  e.preventDefault();
  const id = document.getElementById("adminNewId").value.trim().toUpperCase();
  const name = document.getElementById("adminNewName").value.trim();
  const company = document.getElementById("adminNewCompany").value.trim();
  const price = parseFloat(document.getElementById("adminNewPrice").value);
  const stock = parseInt(document.getElementById("adminNewStock").value);
  const cat = document.getElementById("adminNewCategory").value.trim();

  if (state.products.some(p => p.productId === id)) {
    showToast(`Product ID ${id} already exists!`, "❌");
    return;
  }

  state.products.push({
    productId: id,
    name: name,
    description: `${company} brand ${cat} product with official Indian warranty`,
    price: price,
    mrp: Math.round(price * 1.15),
    stockQuantity: stock,
    companyName: company,
    category: cat,
    rating: 4.8,
    reviews: 120,
    badge: "New Arrival"
  });

  renderCatalog();
  e.target.reset();
  showToast(`Added ${name} to product catalog!`, "✅");
}

function renderAdminCustomers() {
  const container = document.getElementById("adminCustomerList");
  if (!container) return;

  const search = (document.getElementById("adminCustSearch")?.value || "").toLowerCase();
  const filtered = state.customers.filter(c =>
    c.name.toLowerCase().includes(search) ||
    c.city.toLowerCase().includes(search) ||
    c.phone.toLowerCase().includes(search) ||
    c.email.toLowerCase().includes(search)
  );

  document.getElementById("adminCustomerCountBadge").textContent = `${state.customers.length} Customers in Pool`;

  container.innerHTML = filtered.map(c => `
    <div class="cust-item">
      <strong>${escapeHtml(c.name)} (${c.id})</strong>
      <div>📧 ${escapeHtml(c.email)} • 📞 ${escapeHtml(c.phone)}</div>
      <div style="color: #94a3b8;">📍 ${escapeHtml(c.address.street)}, ${escapeHtml(c.city)}, ${escapeHtml(c.address.state)} - ${c.address.zipCode}</div>
      <div style="margin-top: 4px; font-weight: 700; color: ${c.subscription ? '#6ee7b7' : '#94a3b8'};">
        ${c.subscription ? `⭐ BharatPrime ${c.subscription.tier} Member` : "Standard Account"}
      </div>
    </div>
  `).join("");
}

function filterAdminCustomers() {
  renderAdminCustomers();
}

function handleAdminIssueGiftCard(e) {
  e.preventDefault();
  const code = document.getElementById("adminGcCode").value.trim().toUpperCase();
  const pin = document.getElementById("adminGcPin").value.trim();
  const amt = parseFloat(document.getElementById("adminGcAmt").value);

  if (state.giftCards.some(g => g.code === code)) {
    showToast(`Gift voucher code ${code} already exists!`, "❌");
    return;
  }

  state.giftCards.push({ code: code, pin: pin, balance: amt, active: true });
  renderActiveGiftCards();
  e.target.reset();
  showToast(`Festive Gift Voucher ${code} (${formatINR(amt)}) issued!`, "🎁");
}

function renderActiveGiftCards() {
  const container = document.getElementById("activeGiftCardsList");
  if (!container) return;

  container.innerHTML = state.giftCards.map(gc => `
    <div class="gc-pill">
      <div>
        <code>${gc.code}</code> (PIN: ${gc.pin})
      </div>
      <strong style="color: #6ee7b7;">${formatINR(gc.balance)}</strong>
    </div>
  `).join("");
}

// ==========================================
// 11. Utilities & Toast Notifications
// ==========================================

function showToast(message, icon = "ℹ️") {
  const toast = document.getElementById("toastNotification");
  document.getElementById("toastIcon").textContent = icon;
  document.getElementById("toastMessage").textContent = message;

  toast.classList.add("show");
  setTimeout(() => {
    toast.classList.remove("show");
  }, 3500);
}

function escapeHtml(text) {
  if (!text) return "";
  return String(text)
    .replace(/&/g, "&amp;")
    .replace(/</g, "&lt;")
    .replace(/>/g, "&gt;")
    .replace(/"/g, "&quot;")
    .replace(/'/g, "&#039;");
}
