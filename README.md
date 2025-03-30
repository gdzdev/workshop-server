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
#### **Respuesta**:
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
### Obtener todo los productos (Paginación)
- **Método**: `GET`
- **URL**: `/api/v1/products/page?page=0&size=10&sort=name`
####  Parámetros:

Estos parametros son opcionales
- `page`: Número de página -- por defecto `0`
- `size`: Cantidad de items por página -- por defecto `10`
- `sort`: Campo(s) de ordenamiento (ej: `name,asc`, `code, desc`) -- Por defecto `Sin ordenamiento`

#### **Respuesta:**
```json

    {
    "status": "success",
    "response": {
    "content": [
        {
        "id": 1,
        "code": "R001",
        "name": "Filtro de Aceite para Moto",
        "imageUrl": "https://example.com/oil-filter-moto.jpg",
        "stock": 50,
        "cost": 8.50,
        "price": 15.99,
        "categoryName": "Repuestos de Motor",
        "available": true,
        "createdAt": "2025-03-29 17:54:41",
        "updatedAt": "2025-03-29 17:54:41"
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 10,
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
            "offset": 0,
            "paged": true,
            "unpaged": false
        },
            "totalPages": 3,
            "totalElements": 30,
            "last": false,
            "first": true,
            "size": 10,
            "number": 0,
            "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
            "numberOfElements": 10,
            "empty": false
        }
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

#### **Respuesta**:
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
#### **Respuesta (`200`, OK)**:
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

#### **Respuesta (`400`, BAD REQUEST)**:

Mostrara un json con las validaciones que no pasaron y los mensajes de error

```json
{
    "status": "failed",
    "response": {
        "message": "Validation error",
        "errors": {
            "code": "El código no puede estar vacío",
            "cost": "El costo debe ser mayor a 0",
            "price": "El precio debe ser mayor a 0",
            "name": "El nombre no puede estar vacío"
        },
        "timestamp": "2025-03-30",
        "status": 400
    }
}
```

#### **Respuesta (`404`, NOT FOUNT)**

Si la categoria no existe

```json
{
    "status": "failed",
    "response": {
        "message": "Category not found",
        "timestamp": "2025-03-30",
        "status": 404
    }
}
```

### Actualizar un producto

- **Método**: `PUT`

- **URL**: `/api/v1/products/{id}`

#### **Parámetros**:

- **id**: ID del producto a actualizar.

- **Cuerpo de la solicitud**: Similar a la creación.

##### **Respuesta: El producto actualizado ().**

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
#### **Respuesta (`400`, BAD REQUEST)**:

Mostrara un json con las validaciones que no pasaron y los mensajes de error

```json
{
    "status": "failed",
    "response": {
        "message": "Validation error",
        "errors": {
            "code": "El código no puede estar vacío",
            "cost": "El costo debe ser mayor a 0",
            "price": "El precio debe ser mayor a 0",
            "name": "El nombre no puede estar vacío"
        },
        "timestamp": "2025-03-30",
        "status": 400
    }
}
```

#### **Respuesta (`404`, NOT FOUNT)**

Si la categoria no existe

```json
{
    "status": "failed",
    "response": {
        "message": "Category not found",
        "timestamp": "2025-03-30",
        "status": 404
    }
}
```
## Eliminar un producto
- **Método**: DELETE

- **URL**: `/api/v1/products/{id}`

#### Parámetros:

- **id**: ID del producto a eliminar.
- 
#### **Respuesta**: Código de estado (`204`, NO CONTENT).

Si todo sale bien

#### **Respuesta (`404`, NOT FOUNT)**

Si el producto que se trata de eliminar no existe

```json
{
  "status": "failed",
  "response": {
    "message": "Product with id 200 not found",
    "timestamp": "2025-03-30",
    "status": 404
  }
}
```

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

#### **Respuesta: (`200`, OK)**

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
#### **Respuesta**: Código de estado 204 No Content.
Si todo sale bien

#### **Respuesta**: (404, NOT FOUNT)
Si no se encuentra
```json
{
    "status": "failed",
    "response": {
        "message cart": "Category with id '100' not found",
        "timestamp": "2025-03-30",
        "status": 404
    }
}
```

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
                "id": 1,
                "type": "PRODUCT",
                "quantity": 3,
                "product": {
                      "id": 4,
                      "code": "R004",
                      "name": "Carburador para Moto 150cc",
                      "stock": 20,
                      "price": 85.00
                },
                "subTotal": 255.00
              }
            ],
        "grandTotal": 255.00
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
#### **Cuerpo de la solicitud**:
```json
    {
      "item_id": 1,
      "quantity": 1
    }
```
#### **Respuesta**:
```json
    {
      "status": "success",
      "response": {
        "cart_message": "Product added to cart"
      }
    }
```

### Incrementar Cantidad +
- **Metodo**: PATCH
- **URL**: `/api/v1/carts/items/{ItemId}/increase`
#### **Parametros**

Pasar el `ID` del item
#### **Respuesta: (`200`, OK)**
```json
{
    "status": "success",
    "response": {
        "cart_message": "Increased item quantity"
    }
}
```

### Decrementar Cantidad -
- **Metodo**: PATCH
- **URL**: `/api/v1/carts/items/{ItemtId}/decrease`
#### **Parametros**

Pasar el `ID` del item

#### **Respuesta: (`200`, OK)**

```json
{
  "status": "success",
  "response": {
    "cart_message": "Decreased item quantity"
  }
}
```
### Eliminar producto al carrito
- **Método**: DELETE
- **URL**: `/api/v1/carts/items/{ItemId}`
#### **Parametros**: 

Pasar el `ID` del item
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
#### **Respuesta: (`204`, NO CONTENT)**
