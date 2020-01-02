package com.curtisnewbie.rest;

import com.curtisnewbie.model.Book;
import com.curtisnewbie.repository.BookRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * RestApi
 */
@Path("/books")
public class BookEndPoint {

    @Inject
    private BookRepository bookRespository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /** create response in forms of Json */
    public Response getBooks() {
        List<Book> books = bookRespository.findAll();
        if (books.size() > 0) {
            return Response.ok(books).build();
        } else {
            // return 204 HTTP response
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public Response countBooks() {
        long numOfBooks = bookRespository.countAll();
        return Response.ok(numOfBooks).build();
    }

    // create dummy data
    @GET
    @Path("/dummy")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDummy() {
        Book dummy = new Book();
        dummy.setTitle("DummyTitle");
        dummy.setUnitCost(15f);
        dummy.setDescription("blablabla");
        bookRespository.create(dummy);
        return Response.ok(dummy).build();
    }


}
