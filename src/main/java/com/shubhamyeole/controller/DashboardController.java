package com.shubhamyeole.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.shubhamyeole.entity.Movie;
import com.shubhamyeole.entity.MovieCast;
import com.shubhamyeole.entity.MovieTemp;
import com.shubhamyeole.service.MovieCastService;
import com.shubhamyeole.service.MovieService;

//@RestController // is combination of @Controller and @ResponseBody
@Controller
@ResponseBody
@RequestMapping(path="/dashboard")
public class DashboardController {

	@Autowired
	MovieService m_service;

	@Autowired
	MovieCastService mc_service;

	@RequestMapping(method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Movie> findAll(){
		return m_service.findAll();
	}

	//	@RequestMapping(method=RequestMethod.GET, path="{id}" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	//	public Employee findOne(@PathVariable("id") String empId){
	//		return m_service.findOne(empId);
	//
	//	}
//	MovieTemp
	@RequestMapping(method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Movie create(@RequestBody Movie movie){
		Movie mov = new Movie();
		mc_service.create(movie.getMovieCast());
		m_service.create(movie);
		System.out.println(movie);
		return movie;
	}

	@RequestMapping(path="/check", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Movie createTemp(@RequestBody MovieTemp movTemp){
		Movie mov = new Movie();
		mov.setTitle(movTemp.getTitle());
		mov.setYear(movTemp.getYear());
		mov.setRated(movTemp.getRated());
		mov.setReleased(movTemp.getReleased());
		mov.setRuntime(movTemp.getRuntime());
		mov.setDirector(movTemp.getDirector());
		mov.setActors(movTemp.getActors());
		mov.setWriter(movTemp.getWriter());
		mov.setPlot(movTemp.getPlot());
		mov.setLanguage(movTemp.getLanguage());
		mov.setCountry(movTemp.getCountry());
		mov.setAwards(movTemp.getAwards());
		mov.setPoster(movTemp.getPoster());
		mov.setType(movTemp.getType());
		
		MovieCast mc = new MovieCast();
		mc.setImdbId(movTemp.getImdbId());
		mc.setImdbRating(movTemp.getImdbRating());
		mc.setImdbVotes(movTemp.getImdbVotes());
		mc.setMetaStore(movTemp.getMetaStore());
		mc_service.create(mc);

		mov.setMovieCast(mc);
		m_service.create(mov);
		System.out.println(mov);
		return mov;
	}

	//	@RequestMapping(method=RequestMethod.POST)
	//	public String create(@RequestParam(required=false, value="fname") String fname){
	//		System.out.println(fname);
	//		return fname;
	//	}

	//	@RequestMapping(method=RequestMethod.PUT, path="{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	//	public Employee update(@PathVariable("id") String empId, @RequestBody Employee emp){
	//		return m_service.update(emp);
	//	}

	//	@RequestMapping(method=RequestMethod.DELETE, path="{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	//	public void delete(@PathVariable("id") String empId){
	//
	//	}
}
