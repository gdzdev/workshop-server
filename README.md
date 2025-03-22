# API Documentation 📚

Esta documentación describe los endpoints disponibles en la API para gestionar **productos**. Todos los endpoints retornan respuestas en formato JSON.

---

## Base URL
La base URL para acceder a la API es: https://workshop-server.up.railway.app



---

## Productos 🛒

### Obtener todos los productos
- **Método**: `GET`
- **URL**: `/api/v1/products`
- **Respuesta**:
```json
  {
    "status": "success",
    "response": [
      {
        "id": 1,
        "code": "P001",
        "name": "Product A",
        "imageUrl": "http://example.com/image.jpg",
        "stock": 100,
        "cost": 50.00,
        "price": 100.00,
        "categoryName": "Category B",
        "available": true,
        "createdAt": "2025-03-22 10:00:00",
        "updatedAt": "2025-03-22 12:00:00"
      }
    ]
  }
```

## Buscar productos por palabra clave
- **Método**: `GET`

- **URL**: `/api/v1/products/search?keyword={keyword}`

#### Parámetros:

- **keyword**: Palabra clave para buscar en el nombre o código del producto.
- **Respuesta**: Similar a la lista de productos pero con concidencias.
```json
  {
    "status": "success",
    "response": [
      {
        "id": 1,
        "code": "P001",
        "name": "Product A",
        "imageUrl": "http://example.com/image.jpg",
        "stock": 100,
        "cost": 50.00,
        "price": 100.00,
        "categoryName": "Category B",
        "available": true,
        "createdAt": "2025-03-22 10:00:00",
        "updatedAt": "2025-03-22 12:00:00"
      }
    ]
  }
```

## Obtener un producto por ID
- **Método**: `GET`

- **URL**: `/api/v1/products/{id}`

#### Parámetros:

- **id**: ID del producto.

- **Respuesta**:
```json
  {
    "status": "success",
    "response": {
      "id": 1,
      "code": "P00F",
      "name": "Product F",
      "imageUrl": "http://example.com/image.jpg",
      "stock": 100,
      "cost": 50.00,
      "price": 100.00,
      "categoryName": "Category A",
      "available": true,
      "createdAt": "2025-03-22 10:00:00",
      "updatedAt": "2025-03-22 12:00:00"
    }
  }
```

## Crear un producto

- **Método**: `POST`

- **URL**: `/api/v1/products`

- **Cuerpo de la solicitud**:
```json
  {
    "code": "P001",
    "name": "Product 1",
    "imageUrl": "http://example.com/image.jpg",
    "stock": 100,
    "cost": 50.00,
    "price": 100.00,
    "categoryId": 1,
    "available": true
  }
```
- **Respuesta**:
```json
  {
    "status": "success",
    "response": {
        "id": 31,
        "code": "Z00010",
        "name": "New Product D",
        "imageUrl": "https://example.com/images.jpg",
        "stock": 1000,
        "cost": 1.00,
        "price": 1.34,
        "categoryName": "Category S",
        "available": true,
        "createdAt": "2025-03-21 23:56:09",
        "updatedAt": "2025-03-21 23:56:09"
    }
  } 
```

## Actualizar un producto

- **Método**: `PUT`

- **URL**: `/api/v1/products/{id}`

#### **Parámetros**:

- **id**: ID del producto a actualizar.

- **Cuerpo de la solicitud**: Similar a la creación.

Respuesta: El producto actualizado.

```json
  {
    "status": "success",
    "response": {
        "id": 31,
        "code": "Z00010",
        "name": "Update Product D",
        "imageUrl": "https://example.com/images.jpg",
        "stock": 1000,
        "cost": 1.00,
        "price": 1.34,
        "categoryName": "Category S",
        "available": true,
        "createdAt": "2025-03-21 23:56:09",
        "updatedAt": "2025-03-21 23:56:09"
    }
  } 
```

## Eliminar un producto
- **Método**: DELETE

- **URL**: `/api/v1/products/{id}`

#### Parámetros:

- **id**: ID del producto a eliminar.

- **Respuesta**: Código de estado 204 No Content.

