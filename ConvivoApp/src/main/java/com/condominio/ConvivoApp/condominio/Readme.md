# Modulo Condominio – ConvivoApp

Questo modulo gestisce la logica relativa ai **condomini**, alle **unità immobiliari** e alle **membership utente-unità**.  
Fa parte del progetto **ConvivoApp** ed è sviluppato con **Java 21, Spring Boot, Maven, Lombok, JPA, Validation, MySQL**.

---

## 🎯 Obiettivi
- CRUD completo per **Condomini** e **Unità**
- Gestione relazioni **Utente ↔ Unità** con ruoli e stato di verifica
- API REST pronte per integrazione frontend
- Validazione input e gestione errori centralizzata

---

## 📂 Struttura del modulo

- `entity/` → Entità JPA (`Condominium`, `Unit`, `UserUnitMembership`, `UserUnitMembershipId`)
- `repository/` → Repository Spring Data JPA
- `service/` → Logica di business (CRUD, validazioni, regole di appartenenza)
- `dto/` → Oggetti di trasferimento dati (`CreateCondoRequest`, `CreateUnitRequest`, `AddMembershipRequest`)
- `controller/` → API REST (`CondominiumController`, `UnitController`, `MembershipController`)
- `GlobalExceptionHandler` → Gestione errori uniforme

---

## 🚀 API disponibili

### Condomini
- **POST** `/api/condominiums` → Crea un nuovo condominio
  ```json
  {
    "name": "Condominio Aurora",
    "address": "Via Roma 12",
    "city": "Camaiore",
    "postcode": "55041",
    "metadata": "{\"ascensore\":true}"
  }
  
### GET
# - GET /api/condominius -> Lista di tutti i condomini
# - GET /api/condominius/{id} -> Recupera un condominio per ID

### UNITA'
# - POST /api/units -> Crea una nuova unità

# Json
{
    "condominiumId": 1,
    "unitNumber": "A12",
    "floor": "2",
    "sqM": 80,
    "metadata": "{\"balcone\":true}"
}

# - GET /api/units/condominium/{id} -> Lista unità di un condominio
# - GET /api/units/{id} -> Recupera un'unità per ID

### MEMBERSHIP
# - POST /api/membership -> Aggiunge una membership utente-unità

# Json
{
"userId": "550e8400-e29b-41d4-a716-446655440000",
"unitId": 1,
"roleInUnit": "owner",
"verified": true
}

# - GET /api/membership/unit/{id} -> Lista membership di una unità
# - GET /api/membership/user/{id} ->  Lista membership di un utente

### GESTIONE ERRORI
# Gli errori vengono gestiti da GlobalExceptionHandler e restituiti come JSON uniforme:
# - 400 Bad Request → Validazione fallita o input non valido
# Json
{
  "name": "must not be blank"
}

# - 404 Not Found → Risorsa non trovata
# Json
{
  "error": "Condominium not found"
}

# - 409 Conflict → Conflitto (es. membership duplicata)
# Json
{
  "error": "Membership already exists"
}

# - 500 Internal Server Error → Errore generico
# Json 
{
  "error": "Internal server error"
}

### COMANDI BASH
## CREAZIONE CONDOMINIO
curl -X POST http://localhost:8080/api/condominiums \
-H "Content-Type: application/json" \
-d '{"name":"Condominio Aurora","address":"Via Roma 12","city":"Camaiore","postcode":"55041","metadata":"{\"ascensore\":true}"}'

## LISTA CONDOMINI
curl http://localhost:8080/api/condominiums

## CREAZIONE UNITA'
curl -X POST http://localhost:8080/api/units \
-H "Content-Type: application/json" \
-d '{"condominiumId":1,"unitNumber":"A12","floor":"2","sqM":80,"metadata":"{\"balcone\":true}"}'

## AGGIUNTA MEMBERSHIP
curl -X POST http://localhost:8080/api/memberships \
-H "Content-Type: application/json" \
-d '{"userId":"550e8400-e29b-41d4-a716-446655440000","unitId":1,"roleInUnit":"owner","verified":true}'

