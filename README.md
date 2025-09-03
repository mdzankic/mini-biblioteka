# Mini biblioteka

Jednostavna Spring Boot aplikacija za evidenciju knjiga.  
Projekt je izrađen u sklopu kolegija **Programiranje u Javi**.

## Funkcionalnosti
- Registracija i prijava korisnika
- Različite uloge (ADMIN i USER)
- CRUD operacije nad knjigama (dodavanje, uređivanje, brisanje, pregled)
- MySQL baza podataka
- Docker podrška

## Pokretanje
1. Pokrenuti MySQL bazu (npr. preko Docker containera).
2. U IntelliJ IDEA pokrenuti klasu `MiniBibliotekaApplication`.
3. Aplikacija se pokreće na adresi:  
   `http://localhost:8080`

### Podaci za prijavu
- **admin / admin**

## Primjeri API ruta
- `GET /api/books` – pregled knjiga
- `POST /api/books` – dodavanje knjige (ADMIN)
- `PUT /api/books/{id}` – izmjena knjige (ADMIN)
- `DELETE /api/books/{id}` – brisanje knjige (ADMIN)
- `POST /api/auth/register` – registracija korisnika
