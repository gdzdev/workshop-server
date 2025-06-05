# Description
This project is a collaborative effort by myself and  [gdzdev](https://github.com/gdzdev).


## Information About Project

You can test the endpoint using Swagger in by following path `http://localhost:3000/swagger-ui.html`.

This API contained the following endpoint for a **Inventory System**.

- 📱 products
- 💸 purchases
- 🛒 cart
- 🔒 authentication
- 🎯 categories
- 🗒️ reports
- 💲 sales

## Technology And Libraries
This project is done in **[Java Spring Boot framework](https://spring.io/)**.

contained severus libraries like:
- MapStruct.
- MySQL Connector/J.
- Cloudinary HTTP5.
- Project Lombok.
- Springdoc OpenAPI Starter WebMVC UI.




### Example connected db

```java

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
  
public class MySqlExample {
  public static void main(String[] args) throws ClassNotFoundException {
    String host, port, databaseName, userName, password;
    host = port = databaseName = userName = password = null;
    for (int i = 0; i < args.length - 1; i++) {
      switch (args[i].toLowerCase(Locale.ROOT)) {
        case "-host": host = args[++i]; break;
        case "-username": userName = args[++i]; break;
        case "-password": password = args[++i]; break;
        case "-database": databaseName = args[++i]; break;
        case "-port": port = args[++i]; break;
      }
    }
    // JDBC allows to have nullable username and password
    if (host == null || port == null || databaseName == null) {
      System.out.println("Host, port, database information is required");
      return;
    }
    Class.forName("com.mysql.cj.jdbc.Driver");
    try (final Connection connection =
                DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + databaseName + "?sslmode=require", userName, password);
         final Statement statement = connection.createStatement();
         final ResultSet resultSet = statement.executeQuery("SELECT version() AS version")) {

      while (resultSet.next()) {
        System.out.println("Version: " + resultSet.getString("version"));
      }
    } catch (SQLException e) {
      System.out.println("Connection failure.");
      e.printStackTrace();
    }
  }
}
```


```python
import pymysql

timeout = 10
connection = pymysql.connect(
  charset="utf8mb4",
  connect_timeout=timeout,
  cursorclass=pymysql.cursors.DictCursor,
  db="defaultdb",
  host="simplified-inventory-system-simplified-inventory-system.g.aivencloud.com",
  password="************************",
  read_timeout=timeout,
  port=21347,
  user="avnadmin",
  write_timeout=timeout,
)
  
try:
  cursor = connection.cursor()
  cursor.execute("CREATE TABLE mytest (id INTEGER PRIMARY KEY)")
  cursor.execute("INSERT INTO mytest (id) VALUES (1), (2)")
  cursor.execute("SELECT * FROM mytest")
  print(cursor.fetchall())
finally:
  connection.close()

```

```php
<?php

$uri = "mysql://avnadmin:AVNS_ADgd7R9CbUVQ6rh8TL5@simplified-inventory-system-simplified-inventory-system.g.aivencloud.com:21347/defaultdb?ssl-mode=REQUIRED";

$fields = parse_url($uri);

// build the DSN including SSL settings
$conn = "mysql:";
$conn .= "host=" . $fields["host"];
$conn .= ";port=" . $fields["port"];;
$conn .= ";dbname=defaultdb";
$conn .= ";sslmode=verify-ca;sslrootcert=ca.pem";

try {
  $db = new PDO($conn, $fields["user"], $fields["pass"]);

  $stmt = $db->query("SELECT VERSION()");
  print($stmt->fetch()[0]);
} catch (Exception $e) {
  echo "Error: " . $e->getMessage();
}
```