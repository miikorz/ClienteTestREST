/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.productocategoriasjson;

import com.fpmislata.domain.Producto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author alumno
 */
public class ProductoClienteTest {
    public static void main(String[] args) throws IOException {
        // ***********************
        // *** LISTAR PRODUCTO
        // ***********************     
        /*System.out.println("Lista de productos del sistema");
        System.out.println("---------------------------");
        List<Producto> lista = getListProducto("http://localhost:8080/ProyectoFinal20162017-web/webservice/ProductoService/Productos");
        for (Producto producto : lista) {
            System.out.println(producto.toString());
        }
        System.out.println("----------------------------\n"); */

        // **********************************************
        // *** RECUPERAMOS UNA PRODUCTO EN CONCRETO
        // **********************************************
        System.out.println("Recuperando una producto en concrero del sistema");
        Producto p = getProducto("http://localhost:8080/ProyectoFinal20162017-web/webservice/ProductoService/Producto/findById/1");
        System.out.println("La producto recuperada es: " + p.toString());
        System.out.println("----------------------------\n");

        // **********************************************
        // *** AÑADIMOS UNA PRODUCTO AL SISTEMA
        // **********************************************        
        /*Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre("Nuevisimo producto"); 

        System.out.println("Insertando una nueva producto en el sistema");
        Producto p2 = addProducto("http://localhost:8080/ProyectoFinal20162017-web/webservice/ProductoService/Productos/add", nuevoProducto);
        System.out.println("La producto insertada es: " + p2.toString());
        System.out.println("----------------------------\n"); */

        // **********************************************
        // *** ACTUALIZAMOS UN PRODUCTO EN EL SISTEMA
        // **********************************************        
        /*Producto productoExistente = new Producto();
        productoExistente.setNombre("producto existente2");
        productoExistente.setApellidos("apellidos");
        productoExistente.setCodigopostal("46920");
        productoExistente.setDireccion("direccion");
        productoExistente.setNif("nif");
        productoExistente.setPoblacion("poblacion");
        productoExistente.setProvincia("provincia");
        productoExistente.setTelefono("telefono");

        System.out.println("Modificando una producto en el sistema");
        Producto p3 = updateProducto("http://localhost:8080/ProyectoFinal20162017-web/webservice/ProductoService/Productos/update/2", productoExistente);
        System.out.println("La producto modificada es ahora: " + p3.toString());
        System.out.println("----------------------------\n"); */

        // **********************************************
        // *** BORRAMOS UNA PRODUCTO AL SISTEMA
        // **********************************************         
        System.out.println("Recuperando una producto en concrero del sistema");
        deleteProducto("http://localhost:8080/ProyectoFinal20162017-web/webservice/ProductoService/Productos/delete/2");
        System.out.println("La producto recuperada es: " + p.toString());
        System.out.println("----------------------------\n");
    }

    // Obtenemos la lista de productos
    private static List<Producto> getListProducto(String url) throws IOException {
        // Crea el producto para realizar la conexion
        DefaultHttpClient httpClient = new DefaultHttpClient();
        // Crea el método con el que va a realizar la operacion
        HttpGet httpGet = new HttpGet(url);
        // Añade las cabeceras al metodo
        httpGet.addHeader("accept", "application/json; charset=UTF-8");
        httpGet.addHeader("Content-type", "application/json; charset=UTF-8");
        // Invocamos el servicio rest y obtenemos la respuesta
        HttpResponse response = httpClient.execute(httpGet);
        // Obtenemos un objeto String como respuesta del response
        String lista = readObject(response);
        // Creamos el objeto Gson que parseará los objetos a JSON, excluyendo los que no tienen la anotacion @Expose
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        // Creamos el tipo generico que nos va a permitir devolver la lista a partir del JSON que esta en el String
        Type type = new TypeToken<List<Producto>>() {
        }.getType();
        // Parseamos el String lista a un objeto con el gson, devolviendo así un objeto List<Producto>
        return gson.fromJson(lista, type);
    }

    // Obtenemos una producto en concreto
    private static Producto getProducto(String url) throws IOException {
        // Crea el producto para realizar la conexion
        DefaultHttpClient httpClient = new DefaultHttpClient();
        // Crea el método con el que va a realizar la operacion
        HttpGet httpGet = new HttpGet(url);
        // Añade las cabeceras al metodo
        httpGet.addHeader("accept", "application/json; charset=UTF-8");
        httpGet.addHeader("Content-type", "application/json; charset=UTF-8");
        // Invocamos el servicio rest y obtenemos la respuesta
        HttpResponse response = httpClient.execute(httpGet);
        // Obtenemos un objeto String como respuesta del response
        String productoString = readObject(response);
        // Creamos el objeto Gson que parseará los objetos a JSON, excluyendo los que no tienen la anotacion @Expose
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        // Parseamos el String producto a un objeto con el gson, devolviendo así un objeto Producto
        return gson.fromJson(productoString, Producto.class);
    }

    // Añadimos una producto al sistema
    private static Producto addProducto(String url, Producto producto) throws IOException {
        // Crea el producto para realizar la conexion
        DefaultHttpClient httpClient = new DefaultHttpClient();
        // Crea el método con el que va a realizar la operacion
        HttpPost httpPost = new HttpPost(url);
        // Añade las cabeceras al metodo
        httpPost.addHeader("accept", "application/json; charset=UTF-8");
        httpPost.addHeader("Content-type", "application/json; charset=UTF-8");
        // Creamos el objeto Gson que parseará los objetos a JSON, excluyendo los que no tienen la anotacion @Expose
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        // Parseamos el objeto a String
        String jsonString = gson.toJson(producto);
        // Construimos el objeto StringEntity indicando que su juego de caracteres es UTF-8
        StringEntity input = new StringEntity(jsonString, "UTF-8");
        // Indicamos que su tipo MIME es JSON
        input.setContentType("application/json");
        // Asignamos la entidad al metodo con el que trabajamos
        httpPost.setEntity(input);
        // Invocamos el servicio rest y obtenemos la respuesta
        HttpResponse response = httpClient.execute(httpPost);

        // Comprobamos si ha fallado
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatusLine().getStatusCode());
        }

        String productoResult = readObject(response);
        return gson.fromJson(productoResult, Producto.class);
    }

    // Borramos una producto al sistema
    private static void deleteProducto(String url) {
        try {
            // Crea el producto para realizar la conexion
            DefaultHttpClient httpClient = new DefaultHttpClient();
            // Crea el método con el que va a realizar la operacion
            HttpDelete delete = new HttpDelete(url);
            // Añade las cabeceras al metodo
            delete.addHeader("accept", "application/json; charset=UTF-8");
            delete.addHeader("Content-type", "application/json; charset=UTF-8");
            // Invocamos el servicio rest y obtenemos la respuesta
            HttpResponse response = httpClient.execute(delete);
            String status = response.getStatusLine().toString();

            // Comprobamos si ha fallado
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            } else {
                System.out.println("Se ha eliminado la producto correctamente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Producto updateProducto(String url, Producto producto) throws IOException {
        // Crea el producto para realizar la conexion
        DefaultHttpClient httpClient = new DefaultHttpClient();
        // Crea el método con el que va a realizar la operacion
        HttpPut httpPut = new HttpPut(url);
        // Añade las cabeceras al metodo
        httpPut.addHeader("accept", "application/json; charset=UTF-8");
        httpPut.addHeader("Content-type", "application/json; charset=UTF-8");
        // Creamos el objeto Gson que parseará los objetos a JSON, excluyendo los que no tienen la anotacion @Expose
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        // Parseamos el objeto a String
        String jsonString = gson.toJson(producto);
        // Construimos el objeto StringEntity indicando que su juego de caracteres es UTF-8
        StringEntity input = new StringEntity(jsonString, "UTF-8");
        // Indicamos que su tipo MIME es JSON
        input.setContentType("application/json");
        // Asignamos la entidad al metodo con el que trabajamos
        httpPut.setEntity(input);
        // Invocamos el servicio rest y obtenemos la respuesta
        HttpResponse response = httpClient.execute(httpPut);

        // Comprobamos si ha fallado

        // Devolvemos el resultado
        String productoResult = readObject(response);
        return gson.fromJson(productoResult, Producto.class);
    }

    // Método que nos sirve para la lectura de los JSON
    private static String readObject(HttpResponse httpResponse) throws IOException {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));
            StringBuffer buffer = new StringBuffer();
            char[] dataLength = new char[1024];
            int read;
            while ((read = bufferedReader.read(dataLength)) != -1) {
                buffer.append(dataLength, 0, read);
            }
            System.out.println(buffer.toString());
            return buffer.toString();
        } catch (Exception e) {
            throw e;
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }
}
