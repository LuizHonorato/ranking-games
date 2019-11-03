package com.jogos.ranking.controllers;

import com.jogos.ranking.dto.GameDTO;
import com.jogos.ranking.entities.Game;
import com.jogos.ranking.response.Response;
import com.jogos.ranking.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("jogos")
public class GameController {

    @Autowired
    private GameService service;

    @GetMapping
    public List findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable long id) {
        return service.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Response<GameDTO>> create(@Valid @RequestBody GameDTO dto, BindingResult result) {
        Response<GameDTO> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Game game = service.save(this.convertDtoToEntity(dto));

        response.setData(this.convertEntitytoDto(game));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<Response<GameDTO>> update(@Valid @RequestBody GameDTO dto, BindingResult result) {
        Response<GameDTO> response = new Response<GameDTO>();

        Optional<Game> game = service.findById(dto.getId());

        if(!game.isPresent()) {
            result.addError(new ObjectError("Game", "Jogo não encontrado"));
        } else {
            if(game.get().getId().compareTo(dto.getId()) != 0) {
                result.addError(new ObjectError("Game", "Você não pode alterar esse jogo"));
            }
        }

        if(result.hasErrors()) {
            result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));

            return ResponseEntity.badRequest().body(response);
        }

        Game saved = service.save(this.convertDtoToEntity(dto));

        response.setData(this.convertEntitytoDto(saved));
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response<String>> delete(@PathVariable("id") Long id) {
        Response<String> response = new Response<String>();

        Optional<Game> game = service.findById(id);

        if(!game.isPresent()) {
            response.getErrors().add("Game não encontrado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        service.deleteById(id);
        response.setData("Game deletado com sucesso");
        return ResponseEntity.ok().body(response);
    }

    private Game convertDtoToEntity(GameDTO dto) {
        Game game = new Game();
        game.setId(dto.getId());
        game.setName(dto.getName());

        return game;
    }

    private GameDTO convertEntitytoDto(Game game) {
        GameDTO dto = new GameDTO();
        dto.setId(game.getId());
        dto.setName(game.getName());

        return dto;
    }

}
