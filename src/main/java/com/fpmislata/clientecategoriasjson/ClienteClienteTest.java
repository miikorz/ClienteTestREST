/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.clientecategoriasjson;

import com.fpmislata.domain.Cliente;
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
public class ClienteClienteTest {

    public static void main(String[] args) throws IOException {
        // ***********************
        // *** LISTAR CLIENTE
        // ***********************     
        /*System.out.println("Lista de clientes del sistema");
        System.out.println("---------------------------");
        List<Cliente> lista = getListCliente("http://localhost:8080/ProyectoFinal20162017-web/webservice/ClienteService/Clientes");
        for (Cliente cliente : lista) {
            System.out.println(cliente.toString());
        }
        System.out.println("----------------------------\n"); */

        // **********************************************
        // *** RECUPERAMOS UNA CLIENTE EN CONCRETO
        // **********************************************
        System.out.println("Recuperando una cliente en concrero del sistema");
        Cliente p = getCliente("http://localhost:8080/ProyectoFinal20162017-web/webservice/ClienteService/Cliente/findById/1");
        System.out.println("La cliente recuperada es: " + p.toString());
        System.out.println("----------------------------\n");

        // **********************************************
        // *** AÑADIMOS UNA CLIENTE AL SISTEMA
        // **********************************************        
        /*Cliente nuevoCliente = new Cliente();
        nuevoCliente.setNombre("Nuevisimo cliente"); 

        System.out.println("Insertando una nueva cliente en el sistema");
        Cliente p2 = addCliente("http://localhost:8080/ProyectoFinal20162017-web/webservice/ClienteService/Clientes/add", nuevoCliente);
        System.out.println("La cliente insertada es: " + p2.toString());
        System.out.println("----------------------------\n"); */

        // **********************************************
        // *** ACTUALIZAMOS UN CLIENTE EN EL SISTEMA
        // **********************************************        
        /*Cliente clienteExistente = new Cliente();
        clienteExistente.setNombre("cliente existente2");
        clienteExistente.setApellidos("apellidos");
        clienteExistente.setCodigopostal("46920");
        clienteExistente.setDireccion("direccion");
        clienteExistente.setNif("nif");
        clienteExistente.setPoblacion("poblacion");
        clienteExistente.setProvincia("provincia");
        clienteExistente.setTelefono("telefono");

        System.out.println("Modificando una cliente en el sistema");
        Cliente p3 = updateCliente("http://localhost:8080/ProyectoFinal20162017-web/webservice/ClienteService/Clientes/update/2", clienteExistente);
        System.out.println("La cliente modificada es ahora: " + p3.toString());
        System.out.println("----------------------------\n"); */

        // **********************************************
        // *** BORRAMOS UNA CLIENTE AL SISTEMA
        // **********************************************         
        System.out.println("Recuperando una cliente en concrero del sistema");
        deleteCliente("http://localhost:8080/ProyectoFinal20162017-web/webservice/ClienteService/Clientes/delete/2");
        System.out.println("La cliente recuperada es: " + p.toString());
        System.out.println("----------------------------\n");
    }

    // Obtenemos la lista de clientes
    private static List<Cliente> getListCliente(String url) throws IOException {
        // Crea el cliente para realizar la conexion
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
        Type type = new TypeToken<List<Cliente>>() {
        }.getType();
        // Parseamos el String lista a un objeto con el gson, devolviendo así un objeto List<Cliente>
        return gson.fromJson(lista, type);
    }

    // Obtenemos una cliente en concreto
    private static Cliente getCliente(String url) throws IOException {
        // Crea el cliente para realizar la conexion
        DefaultHttpClient httpClient = new DefaultHttpClient();
        // Crea el método con el que va a realizar la operacion
        HttpGet httpGet = new HttpGet(url);
        // Añade las cabeceras al metodo
        httpGet.addHeader("accept", "application/json; charset=UTF-8");
        httpGet.addHeader("Content-type", "application/json; charset=UTF-8");
        // Invocamos el servicio rest y obtenemos la respuesta
        HttpResponse response = httpClient.execute(httpGet);
        // Obtenemos un objeto String como respuesta del response
        String clienteString = readObject(response);
        // Creamos el objeto Gson que parseará los objetos a JSON, excluyendo los que no tienen la anotacion @Expose
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        // Parseamos el String cliente a un objeto con el gson, devolviendo así un objeto Cliente
        return gson.fromJson(clienteString, Cliente.class);
    }

    // Añadimos una cliente al sistema
    private static Cliente addCliente(String url, Cliente cliente) throws IOException {
        // Crea el cliente para realizar la conexion
        DefaultHttpClient httpClient = new DefaultHttpClient();
        // Crea el método con el que va a realizar la operacion
        HttpPost httpPost = new HttpPost(url);
        // Añade las cabeceras al metodo
        httpPost.addHeader("accept", "application/json; charset=UTF-8");
        httpPost.addHeader("Content-type", "application/json; charset=UTF-8");
        // Creamos el objeto Gson que parseará los objetos a JSON, excluyendo los que no tienen la anotacion @Expose
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        // Parseamos el objeto a String
        String jsonString = gson.toJson(cliente);
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

        String clienteResult = readObject(response);
        return gson.fromJson(clienteResult, Cliente.class);
    }

    // Borramos una cliente al sistema
    private static void deleteCliente(String url) {
        try {
            // Crea el cliente para realizar la conexion
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
                System.out.println("Se ha eliminado la cliente correctamente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Cliente updateCliente(String url, Cliente cliente) throws IOException {
        // Crea el cliente para realizar la conexion
        DefaultHttpClient httpClient = new DefaultHttpClient();
        // Crea el método con el que va a realizar la operacion
        HttpPut httpPut = new HttpPut(url);
        // Añade las cabeceras al metodo
        httpPut.addHeader("accept", "application/json; charset=UTF-8");
        httpPut.addHeader("Content-type", "application/json; charset=UTF-8");
        // Creamos el objeto Gson que parseará los objetos a JSON, excluyendo los que no tienen la anotacion @Expose
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        // Parseamos el objeto a String
        String jsonString = gson.toJson(cliente);
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
        String clienteResult = readObject(response);
        return gson.fromJson(clienteResult, Cliente.class);
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
