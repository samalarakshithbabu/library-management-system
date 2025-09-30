// Library Management System - JavaScript Implementation
// Demonstrates OOP concepts in JavaScript

// Mock Database (simulating Java backend)
let books = [
    { id: 1, title: 'Java Programming', author: 'James Gosling', publisher: 'Oracle Press', quantity: 5 },
    { id: 2, title: 'Database Systems', author: 'Ramez Elmasri', publisher: 'Pearson', quantity: 3 },
    { id: 3, title: 'Data Structures', author: 'Robert Lafore', publisher: 'Sams Publishing', quantity: 4 }
];

let students = [
    { id: 'S001', name: 'Rakshith', course: 'CSE-DS', year: 4 },
    { id: 'S002', name: 'vaishu', course: 'Information Technology', year: 4 }
    { id: 'S003', name: 'sai', course: 'CSE', year: 3 }

];

let transactions = [];
let nextBookId = 4;

// Navigation
function showSection(sectionName) {
    // Hide all sections
    document.querySelectorAll('.section').forEach(section => {
        section.classList.remove('active');
    });
    
    // Remove active class from all nav buttons
    document.querySelectorAll('.nav-btn').forEach(btn => {
        btn.classList.remove('active');
    });
    
    // Show selected section
    document.getElementById(sectionName).classList.add('active');
    
    // Add active class to clicked button
    event.target.classList.add('active');
    
    // Update dropdowns and displays
    updateDropdowns();
    displayData();
}

// Book Management
document.getElementById('bookForm').addEventListener('submit', function(e) {
    e.preventDefault();
    
    const title = document.getElementById('bookTitle').value;
    const author = document.getElementById('bookAuthor').value;
    const publisher = document.getElementById('bookPublisher').value;
    const quantity = parseInt(document.getElementById('bookQuantity').value);
    
    const newBook = {
        id: nextBookId++,
        title: title,
        author: author,
        publisher: publisher,
        quantity: quantity
    };
    
    books.push(newBook);
    showMessage('Book added successfully!', 'success');
    this.reset();
    displayBooks();
    updateDropdowns();
});

// Student Management
document.getElementById('studentForm').addEventListener('submit', function(e) {
    e.preventDefault();
    
    const id = document.getElementById('studentId').value;
    const name = document.getElementById('studentName').value;
    const course = document.getElementById('studentCourse').value;
    const year = parseInt(document.getElementById('studentYear').value);
    
    // Check if student ID already exists
    if (students.find(s => s.id === id)) {
        showMessage('Student ID already exists!', 'error');
        return;
    }
    
    const newStudent = { id, name, course, year };
    students.push(newStudent);
    showMessage('Student added successfully!', 'success');
    this.reset();
    displayStudents();
    updateDropdowns();
});

// Issue Book
document.getElementById('issueForm').addEventListener('submit', function(e) {
    e.preventDefault();
    
    const studentId = document.getElementById('issueStudentId').value;
    const bookId = parseInt(document.getElementById('issueBookId').value);
    
    const book = books.find(b => b.id === bookId);
    const student = students.find(s => s.id === studentId);
    
    if (book && student && book.quantity > 0) {
        // Check if student already has this book
        const existingTransaction = transactions.find(t => 
            t.studentId === studentId && t.bookId === bookId && !t.returnDate
        );
        
        if (existingTransaction) {
            showMessage('Student already has this book!', 'error');
            return;
        }
        
        book.quantity--;
        const transaction = {
            id: Date.now(),
            studentId: studentId,
            bookId: bookId,
            studentName: student.name,
            bookTitle: book.title,
            issueDate: new Date().toLocaleDateString(),
            dueDate: new Date(Date.now() + 14 * 24 * 60 * 60 * 1000).toLocaleDateString(),
            returnDate: null
        };
        
        transactions.push(transaction);
        showMessage('Book issued successfully!', 'success');
        this.reset();
        displayTransactions();
        displayBooks();
    } else {
        showMessage('Book not available or invalid selection!', 'error');
    }
});

// Return Book
document.getElementById('returnForm').addEventListener('submit', function(e) {
    e.preventDefault();
    
    const studentId = document.getElementById('returnStudentId').value;
    const bookId = parseInt(document.getElementById('returnBookId').value);
    
    const transaction = transactions.find(t => 
        t.studentId === studentId && t.bookId === bookId && !t.returnDate
    );
    
    if (transaction) {
        const book = books.find(b => b.id === bookId);
        book.quantity++;
        transaction.returnDate = new Date().toLocaleDateString();
        
        showMessage('Book returned successfully!', 'success');
        this.reset();
        displayTransactions();
        displayBooks();
    } else {
        showMessage('No active transaction found!', 'error');
    }
});

// Search Books (Polymorphism Demo)
document.getElementById('searchForm').addEventListener('submit', function(e) {
    e.preventDefault();
    
    const query = document.getElementById('searchQuery').value.toLowerCase();
    const searchType = document.querySelector('input[name="searchType"]:checked').value;
    
    let results;
    
    // Method overloading simulation - different search methods
    if (searchType === 'title') {
        results = searchBooksByTitle(query);
    } else {
        results = searchBooksByAuthor(query);
    }
    
    displaySearchResults(results);
});

// Polymorphism - Method Overloading Simulation
function searchBooksByTitle(title) {
    return books.filter(book => 
        book.title.toLowerCase().includes(title)
    );
}

function searchBooksByAuthor(author) {
    return books.filter(book => 
        book.author.toLowerCase().includes(author)
    );
}

// Display Functions
function displayBooks() {
    const container = document.getElementById('booksList');
    container.innerHTML = '';
    
    books.forEach(book => {
        const bookElement = document.createElement('div');
        bookElement.className = 'data-item';
        bookElement.innerHTML = `
            <h4>📖 ${book.title}</h4>
            <p><strong>ID:</strong> ${book.id}</p>
            <p><strong>Author:</strong> ${book.author}</p>
            <p><strong>Publisher:</strong> ${book.publisher}</p>
            <p><strong>Available:</strong> ${book.quantity}</p>
        `;
        container.appendChild(bookElement);
    });
}

function displayStudents() {
    const container = document.getElementById('studentsList');
    container.innerHTML = '';
    
    students.forEach(student => {
        const studentElement = document.createElement('div');
        studentElement.className = 'data-item';
        studentElement.innerHTML = `
            <h4>👨🎓 ${student.name}</h4>
            <p><strong>ID:</strong> ${student.id}</p>
            <p><strong>Course:</strong> ${student.course}</p>
            <p><strong>Year:</strong> ${student.year}</p>
        `;
        container.appendChild(studentElement);
    });
}

function displayTransactions() {
    const container = document.getElementById('transactionsList');
    container.innerHTML = '';
    
    const activeTransactions = transactions.filter(t => !t.returnDate);
    
    if (activeTransactions.length === 0) {
        container.innerHTML = '<p>No active transactions</p>';
        return;
    }
    
    activeTransactions.forEach(transaction => {
        const transactionElement = document.createElement('div');
        transactionElement.className = 'data-item';
        transactionElement.innerHTML = `
            <h4>🔄 ${transaction.bookTitle}</h4>
            <p><strong>Student:</strong> ${transaction.studentName} (${transaction.studentId})</p>
            <p><strong>Issue Date:</strong> ${transaction.issueDate}</p>
            <p><strong>Due Date:</strong> ${transaction.dueDate}</p>
        `;
        container.appendChild(transactionElement);
    });
}

function displaySearchResults(results) {
    const container = document.getElementById('searchResults');
    container.innerHTML = '';
    
    if (results.length === 0) {
        container.innerHTML = '<p>No books found matching your search.</p>';
        return;
    }
    
    results.forEach(book => {
        const bookElement = document.createElement('div');
        bookElement.className = 'data-item';
        bookElement.innerHTML = `
            <h4>📖 ${book.title}</h4>
            <p><strong>ID:</strong> ${book.id}</p>
            <p><strong>Author:</strong> ${book.author}</p>
            <p><strong>Publisher:</strong> ${book.publisher}</p>
            <p><strong>Available:</strong> ${book.quantity}</p>
        `;
        container.appendChild(bookElement);
    });
}

function updateDropdowns() {
    // Update student dropdowns
    const studentSelects = ['issueStudentId', 'returnStudentId'];
    studentSelects.forEach(selectId => {
        const select = document.getElementById(selectId);
        select.innerHTML = '<option value="">Select Student</option>';
        students.forEach(student => {
            select.innerHTML += `<option value="${student.id}">${student.name} (${student.id})</option>`;
        });
    });
    
    // Update book dropdowns
    const issueBookSelect = document.getElementById('issueBookId');
    issueBookSelect.innerHTML = '<option value="">Select Book</option>';
    books.filter(book => book.quantity > 0).forEach(book => {
        issueBookSelect.innerHTML += `<option value="${book.id}">${book.title} (Available: ${book.quantity})</option>`;
    });
    
    // Update return book dropdown (only books currently issued)
    const returnBookSelect = document.getElementById('returnBookId');
    returnBookSelect.innerHTML = '<option value="">Select Book</option>';
    const issuedBooks = [...new Set(transactions.filter(t => !t.returnDate).map(t => t.bookId))];
    issuedBooks.forEach(bookId => {
        const book = books.find(b => b.id === bookId);
        if (book) {
            returnBookSelect.innerHTML += `<option value="${book.id}">${book.title}</option>`;
        }
    });
}

function displayData() {
    displayBooks();
    displayStudents();
    displayTransactions();
}

function showMessage(message, type) {
    // Remove existing messages
    document.querySelectorAll('.success, .error').forEach(msg => msg.remove());
    
    const messageDiv = document.createElement('div');
    messageDiv.className = type;
    messageDiv.textContent = message;
    
    const activeSection = document.querySelector('.section.active');
    activeSection.insertBefore(messageDiv, activeSection.firstChild);
    
    // Auto remove after 3 seconds
    setTimeout(() => {
        messageDiv.remove();
    }, 3000);
}

// Initialize the application
document.addEventListener('DOMContentLoaded', function() {
    displayData();
    updateDropdowns();
});