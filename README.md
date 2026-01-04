# 📚 LibraryMatch

LibraryMatch es una aplicación desarrollada en **Java con Spring Boot** que consume la API pública **Gutendex** (Proyecto Gutenberg) para consultar información de libros, analizar estadísticas de descargas y permitir búsquedas inteligentes por título.

Este proyecto fue realizado como ejercicio práctico para afianzar el uso de:
- APIs externas
- Streams y colecciones
- Programación funcional (lambdas)
- Manejo de datos JSON
- Arquitectura básica con Spring Boot

---

## 🚀 Funcionalidades

- 📊 Mostrar el **total de libros registrados** en la API Gutendex  
- 🔟 Listar el **Top 10 de libros más descargados**
- 📈 Calcular estadísticas de descargas:
  - Promedio
  - Máximo
  - Mínimo
  - Total de libros analizados
- 🔍 Buscar libros por título:
  - Acepta **búsquedas parciales**
  - Ignora **mayúsculas y minúsculas**
  - Ignora **tildes**
  - Permite búsquedas en **español e inglés**
- 📖 Mostrar información del libro encontrado:
  - Título
  - Autor(es)
  - Año de nacimiento y fallecimiento
  - Idiomas
  - Número de descargas

---

## 🛠️ Tecnologías utilizadas

- ☕ **Java 17**
- 🌱 **Spring Boot**
- 📦 **Maven**
- 🌐 **HttpClient (Java.net.http)**
- 🔄 **Jackson (ObjectMapper)**
- 🧵 **Streams & Lambdas**
- 🧠 **Records (Java)**

---

## 🌐 API utilizada

- **Gutendex API**
- Documentación: https://gutendex.com/

---

## 📂 Estructura del proyecto

