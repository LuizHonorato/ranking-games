package com.jogos.ranking.controllers;

import com.jogos.ranking.dto.PlayerDTO;
import com.jogos.ranking.entities.Player;
import com.jogos.ranking.response.Response;
import com.jogos.ranking.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("jogadores")
public class PlayerController {

    @Autowired
    private PlayerService service;

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

    @GetMapping(path = "/ranking")
    public List ranking(){ return service.ranking(); }

    @PostMapping
    public ResponseEntity<Response<PlayerDTO>> create(@Valid @RequestBody PlayerDTO dto, BindingResult result) {
        Response<PlayerDTO> response = new Response<PlayerDTO>();

        if(result.hasErrors()) {
            result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        Player player = service.save(this.convertDtoToEntity(dto));

        response.setData(this.convertEntitytoDto(player));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<Response<PlayerDTO>> update(@Valid @RequestBody PlayerDTO dto, BindingResult result) {
        Response<PlayerDTO> response = new Response<PlayerDTO>();

        Optional<Player> player = service.findById(dto.getId());

        if(!player.isPresent()) {
            result.addError(new ObjectError("Player", "Jogador não encontrado"));
        } else {
            if(player.get().getId().compareTo(dto.getId()) != 0) {
                result.addError(new ObjectError("Player", "Você não pode alterar esse jogador"));
            }
        }

        if(result.hasErrors()) {
            result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));

            return ResponseEntity.badRequest().body(response);
        }

        Player saved = service.save(this.convertDtoToEntity(dto));

        response.setData(this.convertEntitytoDto(saved));
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response<String>> delete(@PathVariable("id") Long id) {
        Response<String> response = new Response<String>();

        Optional<Player> player = service.findById(id);

        if(!player.isPresent()) {
            response.getErrors().add("Jogador não encontrado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        service.deleteById(id);
        response.setData("Jogador deletado com sucesso");
        return ResponseEntity.ok().body(response);
    }

    private Player convertDtoToEntity(PlayerDTO dto) {
        Player player = new Player();
        player.setId(dto.getId());
        player.setName(dto.getName());
        player.setMatches(dto.getMatches());
        player.setVictories(dto.getVictories());

        return player;
    }

    private PlayerDTO convertEntitytoDto(Player player) {
        PlayerDTO dto = new PlayerDTO();
        dto.setId(player.getId());
        dto.setName(player.getName());
        dto.setMatches(player.getMatches());
        dto.setVictories(player.getVictories());

        return dto;
    }
}
