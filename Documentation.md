# 📘 API Contract – Employees Microservice

**Versión:** 1.0.0  
**Título:** Employees microservices  
**Descripción:** API for creating, updating, querying and delete employees  
**Base URL:** `http://localhost:8080`

---

# 1️ Información General

## 🔹 Propósito

Este microservicio permite:

- Crear empleados
- Actualizar empleados
- Consultar empleados
- Eliminar empleados
- Buscar empleados con filtros

---

## 🔹 Formato de Datos

- Content-Type: `application/json`
- Encoding: UTF-8`

---

# 2️ Modelo de Datos

---

## 📌 EmployeeRequestDTO

Campos requeridos:  
`age`, `birthDate`, `firstName`, `gender`, `motherName`, `parentName`, `position`, `status`

| Campo | Tipo | Validaciones |
|-------|------|-------------|
| firstName | String | min:0, max:50 |
| secondName | String | — |
| parentName | String | min:0, max:50 |
| motherName | String | min:0, max:50 |
| age | Integer (int32) | — |
| gender | String | Hombre \| Mujer |
| birthDate | String | Formato YYYY-MM-DD |
| position | String | — |
| status | Boolean | true = activo |

### 📥 Ejemplo

```json
{
  "firstName": "Juan",
  "secondName": "Carlos",
  "parentName": "Perez",
  "motherName": "Lopez",
  "age": 30,
  "gender": "Hombre",
  "birthDate": "1994-01-15",
  "position": "Developer",
  "status": true
}
```

---

## 📌 EmployeeResponseDTO

| Campo | Tipo |
|-------|------|
| firstName | String |
| secondName | String |
| parentName | String |
| motherName | String |
| age | Integer |
| gender | String |
| birthDate | String |
| position | String |
| status | Boolean |
| startDate | String |

---

# 3️ Endpoints

---

## 🔹 3.1 Obtener empleado por ID

```
GET /api/employees/{id}
```

### Parámetros

| Nombre | Tipo | Requerido |
|--------|------|----------|
| id | Long (int64) | Sí |

### Respuestas

| Código | Descripción |
|--------|------------|
| 200 | Query success |
| 400 | Invalid fields |

---

## 🔹 3.2 Actualizar empleado

```
PUT /api/employees/{id}
```

### Parámetros

| Nombre | Tipo | Requerido |
|--------|------|----------|
| id | Long (int64) | Sí |

### Request Body
`EmployeeRequestDTO`

### Respuestas

| Código | Descripción |
|--------|------------|
| 200 | Update success |
| 400 | Filtros inválidos |
| 500 | Internal error |

---

## 🔹 3.3 Eliminar empleado

```
DELETE /api/employees/{id}
```

### Parámetros

| Nombre | Tipo | Requerido |
|--------|------|----------|
| id | Long (int64) | Sí |

### Respuestas

| Código | Descripción |
|--------|------------|
| 200 | Deleted success |
| 401 | Not found |
| 500 | Internal error |

---

## 🔹 3.4 Obtener todos los empleados

```
GET /api/employees
```

### Respuestas

| Código | Descripción |
|--------|------------|
| 200 | Query success |
| 400 | Invalid fields |

### 📤 Response 200

```json
[
  {
    "firstName": "Juan",
    "secondName": "Carlos",
    "parentName": "Perez",
    "motherName": "Lopez",
    "age": 30,
    "gender": "Hombre",
    "birthDate": "1994-01-15",
    "position": "Developer",
    "status": true,
    "startDate": "2023-01-01"
  }
]
```

---

## 🔹 3.5 Crear empleados

```
POST /api/employees
```

### Request Body
Array de `EmployeeRequestDTO`

### Ejemplo

```json
[
  {
    "firstName": "Juan",
    "secondName": "Carlos",
    "parentName": "Perez",
    "motherName": "Lopez",
    "age": 30,
    "gender": "Hombre",
    "birthDate": "1994-01-15",
    "position": "Developer",
    "status": true
  }
]
```

### Respuestas

| Código | Descripción |
|--------|------------|
| 204 | No content and success delete |
| 400 | Invalid fields |
| 500 | Internal error |

---

## 🔹 3.6 Buscar empleados con filtros

```
GET /api/employees/search
```

### Query Params

| Parámetro | Tipo | Requerido |
|-----------|------|----------|
| id | String | No |
| startDate | String | No |
| firstName | String (max 50) | Sí |
| secondName | String | No |
| parentName | String (max 50) | Sí |
| motherName | String (max 50) | Sí |
| age | Integer | Sí |
| gender | String (Hombre \| Mujer) | Sí |
| birthDate | String (YYYY-MM-DD) | Sí |
| position | String | Sí |
| status | Boolean | Sí |

### Respuestas

| Código | Descripción |
|--------|------------|
| 200 | Search success |
| 400 | Invalid filters |
| 500 | Internal error |

---

# 4️ Formato Estándar de Error (Recomendado)

```json
{
  "timestamp": "2026-02-25T10:15:30Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Invalid fields",
  "path": "/api/employees"
}
```

---

# 5️ Reglas de Negocio

- El campo `status` es booleano:
  - `true` = Activo
  - `false` = Inactivo
- `gender` solo acepta: Hombre o Mujer
- `birthDate` debe cumplir formato YYYY-MM-DD
- `firstName`, `parentName`, `motherName` máximo 50 caracteres

---
