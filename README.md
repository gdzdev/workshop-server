# API Documentation 📚

Esta documentación describe los endpoints disponibles en la API para gestionar **productos**. Todos los endpoints retornan respuestas en formato JSON.

---

## Base URL
La base URL para acceder a la API es: https://workshop-server.up.railway.app



---

## Productos 📦

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

### Buscar productos por palabra clave
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

### Obtener un producto por ID
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

### Crear un producto

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

### Actualizar un producto

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

---

## Categorías 📁

### Obtener todas las categorias
- **Método**: `GET`
- **URL**: `/api/v1/categories`
- **Respuesta**:
```json
  {
    "status": "success",
    "response": [
      {
        "id": 1,
        "name": "Categoria A",
        "description": "Descripcion de la categoria A",
        "image_url": "https://example.com/categoria.jpg",
        "created_at": "2025-03-25 21:50:27",
        "update_at": "2025-03-25 21:50:27"
      }
    ]
  }
```

### Buscar categorias por ID
- **Método**: `GET`

- **URL**: `/api/v1/categories/{id}`

#### Parámetros:

- **Id**: Id de la categoria.
- **Respuesta**:
```json
  {
    "status": "success",
    "response": {
      "id": 1,
      "name": "Categoria S",
      "description": "Descripcion de la categoria S",
      "image_url": "https://example.com/categoria.jpg",
      "created_at": "2025-03-25 21:50:27",
      "update_at": "2025-03-25 21:50:27"
    }
  }
```

### Crear una categoria

- **Método**: `POST`

- **URL**: `/api/v1/categories`

- **Cuerpo de la solicitud**:
```json
  {
    "name": "Categoria X",
    "description": "Descripcion de categoria X...",
    "image_url": "http//imagen.jpg"
  }
```
- **Respuesta**:
```json
  {
    "status": "success",
    "response": {
      "id": 7,
      "name": "Categoria X",
      "description": "Descripcion de categoria X...",
      "image_url": "http//imagen.jpg",
      "created_at": "2025-03-25 23:38:53",
      "update_at": "2025-03-25 23:38:53"
    }
  }
```

### Actualizar una categoria

- **Método**: `PUT`

- **URL**: `/api/v1/categories/{id}`

#### **Parámetros**:

- **id**: ID de la categoria a actualizar.

- **Cuerpo de la solicitud**: Similar a la creación.

Respuesta: El producto actualizado.

```json
  {
    "status": "success",
    "response": {
      "id": 7,
      "name": "Categoria XX",
      "description": "Descripcion de categoria XXX...",
      "image_url": "http//imagen.jpg",
      "created_at": "2025-03-25 23:38:53",
      "update_at": "2025-03-25 23:38:53"
  }
}
```

### Eliminar una categoria
- **Método**: DELETE
- **URL**: `/api/v1/categories/{id}`
#### Parámetros:
- **id**: ID de la categoria a eliminar.
- **Respuesta**: Código de estado 204 No Content.
---

## Carrito de Compras 🛒
### Obtener el carrito actual
- **Método**: GET
- **URL**: `/api/v1/carts`
- **Respuesta**:
```json
    {
      "status": "success",
      "response": {
        "cartItems": [
          {
            "id": 5,
            "quantity": 1,
            "product": {
              "id": 10,
              "code": "H005",
              "name": "Producto A",
              "price": 60.00
            },
            "total": 60.00
          }
        ],
        "grandTotal": 60.00
      }
    }
```
### Obtener el total del carrito actual
- **Método**: GET
- **URL**: `/api/v1/carts/total`
- **Respuesta**:
```json
    {
      "status": "success",
      "response": {
        "cart_message": "$ 0.00"
      }
    }
```
### Añadir producto al carrito
- **Método**: POST
- **URL**: `/api/v1/carts/add`
- **Cuerpo de la solicitud**:
```json
    {
      "product_id": 10,
      "quantity": 1
    }
```
- **Respuesta**:
```json
    {
      "status": "success",
      "response": {
        "cart_message": "Product added to cart"
      }
    }
```
### Eliminar producto al carrito
- **Método**: DELETE
- **URL**: `/api/v1/carts/delete/{itemId}`
- **Parametros**: ID del item
- **Respuesta**:
```json
    {
      "status": "success",
      "response": {
        "cart_message": "Product removed from cart"
      }
    }
```
### Vaciar carrito
- **Método**: DELETE
- **URL**: `/api/v1/carts/empty`
- **Respuesta**: Código de estado 204 No Content
