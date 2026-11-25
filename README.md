# Library Management System

A complete library management system for cataloging books, managing members, tracking loans, and generating reports.

---

## What It Does

Full library operations:
- **Catalog Management** - Books, authors, categories, ISBN, copies
- **Member Management** - Registration, membership tiers, fines
- **Loan System** - Checkout, return, renewals, reservations
- **Fine Calculation** - Overdue fines, lost book charges
- **Reports** - Circulation stats, popular books, overdue lists
- **Search** - Full-text search, filters, advanced queries

## Technology Stack

| Layer | Technology |
|-------|------------|
| Backend | Python (Flask/FastAPI) or Java (Spring Boot) |
| Frontend | React / Thymeleaf / JSP |
| Database | MySQL / PostgreSQL / SQLite |
| Auth | JWT / Session with RBAC |

## Project Structure

```
library-management-system/
├── backend/
│   ├── models/              # Book, Member, Loan, Fine, Category
│   ├── routes/              # API endpoints
│   ├── services/            # Business logic
│   └── main.py / Application.java
├── frontend/
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   └── services/
│   └── package.json / pom.xml
├── database/
│   ├── schema.sql
│   └── seed_data.sql
├── tests/
└── README.md
```

## Features

### Implemented
- Book catalog with multiple copies tracking
- Member registration and management
- Loan/return/renewal workflow
- Automatic fine calculation
- Basic search and filtering
- Admin dashboard with key metrics

### In Progress
- Reservation/hold queue
- Email/SMS notifications for due dates
- Barcode/QR code support
- Digital resource management (ebooks, journals)
- Inter-library loan support

## Database Schema (Core)

```sql
books (id, isbn, title, author_id, category_id, publisher, year, total_copies, available_copies)
authors (id, name, bio)
categories (id, name, description)
members (id, member_id, name, email, phone, address, join_date, tier, status)
loans (id, book_id, member_id, checkout_date, due_date, return_date, status)
fines (id, loan_id, member_id, amount, reason, paid_status, date)
reservations (id, book_id, member_id, request_date, expiry_date, status)
```

## Setup

```bash
# Backend (Python example)
cd backend
pip install -r requirements.txt
python init_db.py
python main.py

# Frontend
cd frontend
npm install
npm start
```

---

## Author

**SAMALA RAKSHITH BABU**  

**Contact:**  
- Email: samalarakshithbabu7@gmail.com  
- LinkedIn: https://www.linkedin.com/in/samala-rakshith-babu/  
- GitHub: https://github.com/samalarakshithbabu  

---

## License

MIT License