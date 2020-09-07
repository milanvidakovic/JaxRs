package com.minja.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.minja.beans.NaseljenoMesto;

@Path("/mesto")
public class NaseljenoMestoService {

    private static Map<Long, NaseljenoMesto> mesta = new HashMap<Long, NaseljenoMesto>();

    static {
        NaseljenoMestoService.mesta.put(1L, new NaseljenoMesto(1L, "Novi Sad"));
    }

	@GET
	@Path("/getall")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<NaseljenoMesto> getMesta(){
		return mesta.values();
	}
}