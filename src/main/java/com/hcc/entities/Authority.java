package com.hcc.entities;
//
import javax.persistence.*;
import java.util.Objects;

//
@Entity
public class Authority implements org.springframework.security.core.GrantedAuthority {
//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
    private String authority;
//
   @ManyToOne
   @JoinColumn(name = "user_id", nullable = false)
   private User user;
//
   public Authority() {
   }
//
    public Authority(String authority) {
        this.authority = authority;
   }
//
    @Override
    public String getAuthority() {
        return authority;
    }
//
    // Getters and Setters...
//
 public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
//    // Other methods...
//
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Authority)) return false;
        Authority authority1 = (Authority) o;
        return id.equals(authority1.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", authority='" + authority + '\'' +
                '}';

//
    }
//
}
//

//The provided code defines a JPA entity class named **`Authority`**. This class implements the
// Spring Security **`GrantedAuthority`** interface and represents specific permissions or
// roles (commonly tied to a user) within the system. Here’s a detailed explanation:
//
//---
//
//### **Class Summary**
//```java
//@Entity
//public class Authority implements org.springframework.security.core.GrantedAuthority
//```
//- The `@Entity` annotation marks this class as a JPA entity, meaning it will be mapped to a
// database table called `Authority`.
//- The class implements the **`GrantedAuthority`** interface from Spring Security, which defines a
// single method: `getAuthority()`.
//
//  - The **`GrantedAuthority`** interface is used to represent the security-related permission of
//  the user, typically in the form of roles (e.g., `ROLE_USER`, `ROLE_ADMIN`).
//
//- Each instance of the `Authority` class represents one individual authority/role.
//
//---
//
//### **Fields and Database Mappings**
//
//1. **`id`**:
//```java
//@Id
//   @GeneratedValue(strategy = GenerationType.IDENTITY)
//   private Long id;
//```
//   - This field serves as the primary key for the `Authority` table.
//   - The `@GeneratedValue(strategy = GenerationType.IDENTITY)` annotation ensures that the
//   database auto-generates this ID (usually as an `AUTO_INCREMENT` value).
//
//2. **`authority`**:
//```java
//private String authority;
//```
//   - A `String` field representing the name or type of authority (e.g., a role like `ROLE_USER`,
//   `ROLE_ADMIN`, etc.).
//   - This is the core functionality of the `Authority` class and fulfills the purpose of the
//   `getAuthority()` method in the `GrantedAuthority` interface.
//
//3. **`user`**:
//```java
//@ManyToOne
//   @JoinColumn(name = "user_id", nullable = false)
//   private User user;
//```
//   - This establishes a **many-to-one** relationship with the **`User`** entity.
//   - A single user can have multiple authorities (e.g., a user can be both `ROLE_USER` and
//   `ROLE_ADMIN`), but each `Authority` is associated with exactly one user.
//   - `@JoinColumn` specifies the foreign key column in the `Authority` table, `user_id`,
//   that references the primary key of the `User` table.
//   - `nullable = false` ensures that every authority is always tied to a user.
//
//---
//
//### **Constructors**
//
//1. **Default Constructor**:
//```java
//public Authority() {
//   }
//```
//   - A no-argument constructor is required by JPA to instantiate objects.
//
//2. **Parameterized Constructor**:
//```java
//public Authority(String authority) {
//       this.authority = authority;
//   }
//```
//   - Allows for creating an `Authority` object by simply specifying the authority string
//   (e.g., the role name like `ROLE_USER` or `ROLE_ADMIN`).
//
//---
//
//### **Implementation of `GrantedAuthority`**
//
//```java
//@Override
//public String getAuthority() {
//    return authority;
//}
//```
//- The `getAuthority()` method fulfills the contract of the **`GrantedAuthority`** interface.
//- It returns the value of the `authority` field, which is a String representing the authority
// or role.
//
//  **Usage Note**: Spring Security uses this method internally to retrieve the roles/permissions
//  associated with a user during authentication and authorization processes.
//
//---
//
//### **Getters and Setters**
//The class includes standard getter and setter methods for all fields to enable controlled
// access and modification of the attributes.
//
//#### Example:
//```java
//public User getUser() {
//    return user;
//}
//
//public void setUser(User user) {
//    this.user = user;
//}
//```
//- These methods ensure fields like `user` and `authority` can be accessed and updated as required.
//
//---
//
//### **Equality and HashCode**
//
//1. **`equals()` Method**:
//```java
//@Override
//   public boolean equals(Object o) {
//       if (this == o) return true;
//       if (!(o instanceof Authority)) return false;
//       Authority authority1 = (Authority) o;
//       return id.equals(authority1.id);
//   }
//```
//   - Two `Authority` objects are considered equal if their `id` fields are the same.
//
//2. **`hashCode()` Method**:
//```java
//@Override
//   public int hashCode() {
//       return Objects.hash(id);
//   }
//```
//   - Computes the `hashCode` based on the `id` field.
//
//These methods are important when storing instances in collections like `HashSet` or
// `HashMap`, ensuring proper equality checks.
//
//---
//
//### **`toString()` Method**
//```java
//@Override
//public String toString() {
//    return "Authority{" +
//           "id=" + id +
//           ", authority='" + authority + '\'' +
//           '}';
//}
//```
//- The `toString()` method provides a readable string representation of the `Authority` object.
//- This is helpful for debugging or logging.
//
//---
//
//### **Entity Relationships Summary**
//
//- The **`Authority`** entity is designed to represent roles/permissions in the system.
//- It has a **many-to-one relationship** with the **`User`** entity:
//  - A single user can have *many authorities*.
//  - A single authority belongs to exactly one user.
//
//---
//
//### **Database Table Structure**
//
//In the database, the `Authority` table might look like this:
//
//| **Column Name**    | **Type**         | **Constraint**         |
//|---------------------|------------------|-------------------------|
//| `id`               | AUTO_INCREMENT   | PRIMARY KEY            |
//| `authority`        | VARCHAR(255)     | NOT NULL               |
//| `user_id`          | BIGINT           | FOREIGN KEY REFERENCES `User(id)` |
//
//---
//
//### **Example Usage**
//
//#### Creating an Authority:
//```java
//Authority authority = new Authority("ROLE_USER");
//authority.setUser(user);
//```
//
//#### Assigning to a User:
//```java
//User user = new User();
//user.setUsername("john_doe");
//
//Authority authority1 = new Authority("ROLE_USER");
//authority1.setUser(user);
//
//Authority authority2 = new Authority("ROLE_ADMIN");
//authority2.setUser(user);
//
//List<Authority> authorities = new ArrayList<>();
//authorities.add(authority1);
//authorities.add(authority2);
//
//user.setAuthorities(authorities);
//```
//
//#### Accessing User Authorities:
//```java
//for (GrantedAuthority authority : user.getAuthorities()) {
//    System.out.println(authority.getAuthority());
//}
/// / Output:
/// / ROLE_USER
/// / ROLE_ADMIN
//```
//
//---
//
//### **Summary of Functionality**
//The `Authority` class plays a crucial role in a Spring Security-based authentication system.
// Here's how it fits into the overall application:
//
//1. **Authentication and Authorization**:
//   - Each `Authority` object represents a specific permission or role (e.g., "ROLE_USER",
//   "ROLE_ADMIN").
//   - These roles/permissions are tied to a user and are utilized by Spring Security during
//   authentication and authorization.
//
//2. **Database Mapping**:
//   - The `Authority` entity maps to a table, storing roles/permissions and linking them
//   to corresponding users via a foreign key.
//
//3. **Reusability**:
//   - This class can easily integrate into larger authentication systems, mapping multiple
//   authorities to users.
//
//---
//
//Let me know if you'd like further clarification or examples!