package br.pro.ramon.resources;

import br.pro.ramon.modelos.Aluno;
import br.pro.ramon.modelos.NotaInvalidaException;
import java.net.URI;
import java.util.Locale;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/aluno")
public class AlunoResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN + "; charset=UTF-8")
    public String ex1e2(@QueryParam("nome") String nome, @QueryParam("p1") double p1, @QueryParam("p2") double p2) throws NotaInvalidaException {
        Aluno aluno = new Aluno(nome, p1, p2);
        double media = aluno.getMedia();

        return String.format("O aluno %s está com média %.1f.%n", nome, media);
    }

    @GET
    @Path("/media")
    @Produces(MediaType.TEXT_PLAIN + "; charset=UTF-8")
    public double ex3(@QueryParam("nome") String nome, @QueryParam("p1") double p1, @QueryParam("p2") double p2) throws NotaInvalidaException {
        Aluno aluno = new Aluno(nome, p1, p2);
        double media = aluno.getMedia();

        return media;
    }

    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    public String ex4(@QueryParam("nome") String nome, @QueryParam("p1") double p1, @QueryParam("p2") double p2) throws NotaInvalidaException {
        Aluno aluno = new Aluno(nome, p1, p2);
        double media = aluno.getMedia();
        boolean aprovado = aluno.getAprovado();

        return String.format(Locale.US, "{ \"media\": %.1f, \"aprovado\": %s }", media, aprovado);
    }

    @GET
    @Path("/json-and-status-code")
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    public Response ex5(@QueryParam("nome") String nome, @QueryParam("p1") double p1, @QueryParam("p2") double p2) {
        try {
            Aluno aluno = new Aluno(nome, p1, p2);
            double media = aluno.getMedia();
            boolean aprovado = aluno.getAprovado();

            return Response.status(Response.Status.OK).entity(String.format(Locale.US, "{ \"media\": %.1f, \"aprovado\": %s }", media, aprovado)).build();
        } catch (NullPointerException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(String.format("{ \"erro\": \"%s\" }", "Entre com todos os parâmetros.")).build();
        } catch (NumberFormatException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(String.format("{ \"erro\": \"%s\" }", "Entre com números para p1 e p2.")).build();
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(String.format("{ \"erro\": \"%s\" }", "O nome é obrigatório.")).build();
        } catch (NotaInvalidaException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(String.format("{ \"erro\": \"%s\" }", "Entre com notas válidas para p1 e p2.")).build();
        }
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN + "; charset=UTF-8")
    public String ex6(@FormParam("nome") String nome, @FormParam("p1") double p1, @FormParam("p2") double p2) throws NotaInvalidaException {
        Aluno aluno = new Aluno(nome, p1, p2);
        double media = aluno.getMedia();

        return String.format("O aluno %s está com média %.1f.%n", nome, media);
    }

    @GET
    @Path("/{nome}/{p1}/{p2}")
    @Produces(MediaType.TEXT_PLAIN + "; charset=UTF-8")
    public String ex7(@PathParam("nome") String nome, @PathParam("p1") double p1, @PathParam("p2") double p2) throws NotaInvalidaException {
        Aluno aluno = new Aluno(nome, p1, p2);
        double media = aluno.getMedia();

        return String.format("O aluno %s está com média %.1f.%n", nome, media);
    }

    @GET
    @Path("/redirect/{nome}")
    @Produces(MediaType.TEXT_PLAIN + "; charset=UTF-8")
    public Response ex8(@PathParam("nome") String nome, @QueryParam("p1") double p1, @QueryParam("p2") double p2) {
        return Response.status(Response.Status.FOUND).location(URI.create("aluno/" + nome + "/" + p1 + "/" + p2)).build();
    }

    @GET
    @Path("/jaxb/xml")
    @Produces(MediaType.APPLICATION_XML)
    public Aluno ex9(@QueryParam("nome") String nome, @QueryParam("p1") double p1, @QueryParam("p2") double p2) throws NotaInvalidaException {
        return new Aluno(nome, p1, p2);
    }

    @GET
    @Path("/jaxb/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Aluno ex10(@QueryParam("nome") String nome, @QueryParam("p1") double p1, @QueryParam("p2") double p2) throws NotaInvalidaException {
        return new Aluno(nome, p1, p2);
    }

    @GET
    @Path("/jaxb/json-and-status-code")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ex11(@QueryParam("nome") String nome, @QueryParam("p1") double p1, @QueryParam("p2") double p2) {
        try {
            Aluno aluno = new Aluno(nome, p1, p2);
            return Response.status(Response.Status.OK).entity(aluno).build();
        } catch (NullPointerException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(String.format("{ \"erro\": \"%s\" }", "Entre com todos os parâmetros.")).build();
        } catch (NumberFormatException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(String.format("{ \"erro\": \"%s\" }", "Entre com números para p1 e p2.")).build();
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(String.format("{ \"erro\": \"%s\" }", "O nome é obrigatório.")).build();
        } catch (NotaInvalidaException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(String.format("{ \"erro\": \"%s\" }", "Entre com notas válidas para p1 e p2.")).build();
        }
    }

    @POST
    @Path("/jaxb/json")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Aluno ex12(Aluno aluno) {
        return aluno;
    }

}
