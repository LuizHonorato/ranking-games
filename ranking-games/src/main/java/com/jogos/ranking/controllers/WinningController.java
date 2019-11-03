package com.jogos.ranking.controllers;

import com.jogos.ranking.dto.WinningDTO;
import com.jogos.ranking.entities.Game;
import com.jogos.ranking.entities.Player;
import com.jogos.ranking.entities.Winning;
import com.jogos.ranking.response.Response;
import com.jogos.ranking.services.WinningService;
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
@RequestMapping(path = "partidas")
public class WinningController {

    @Autowired
    WinningService service;

    @GetMapping
    public List findAll() {
        return service.findAll();
    }

    @GetMapping(path = "/ranking")
    public List ranking() {
        return service.ranking();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable long id) {
        return service.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Response<WinningDTO>> create(@Valid @RequestBody WinningDTO dto, BindingResult result) {
        Response<WinningDTO> response = new Response<WinningDTO>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Winning winning = service.save(this.convertDtoToEntity(dto));

        response.setData(this.convertEntitytoDto(winning));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<Response<WinningDTO>> update(@Valid @RequestBody WinningDTO dto, BindingResult result) {
        Response<WinningDTO> response = new Response<WinningDTO>();

        Optional<Winning> winning = service.findById(dto.getId());

        if(!winning.isPresent()) {
            result.addError(new ObjectError("Winning", "Partida não encontrada"));
        } else {
            if(winning.get().getId().compareTo(dto.getId()) != 0) {
                result.addError(new ObjectError("Winning", "Você não pode alterar essa partida"));
            }
        }

        if(result.hasErrors()) {
            result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));

            return ResponseEntity.badRequest().body(response);
        }

        Winning saved = service.save(this.convertDtoToEntity(dto));

        response.setData(this.convertEntitytoDto(saved));
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response<String>> delete(@PathVariable("id") Long id) {
        Response<String> response = new Response<String>();

        Optional<Winning> winning = service.findById(id);

        if(!winning.isPresent()) {
            response.getErrors().add("Partida não encontrada.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        service.deleteById(id);
        response.setData("Partida deletada com sucesso");
        return ResponseEntity.ok().body(response);
    }

    private Winning convertDtoToEntity(WinningDTO dto) {
        Winning winning = new Winning();

        Player player = new Player();
        player.setId(dto.getPlayer_id());

        Game game = new Game();
        game.setId(dto.getGame_id());

        winning.setId(dto.getId());
        winning.setPlayer_id(player);
        winning.setGame_id(game);

        winning.setScoreboard(dto.getScoreboard());

        return winning;
    }

    private WinningDTO convertEntitytoDto(Winning winning) {
        WinningDTO dto = new WinningDTO();
        dto.setId(winning.getId());
        dto.setPlayer_id(winning.getPlayer_id().getId());
        dto.setGame_id(winning.getGame_id().getId());
        dto.setScoreboard(winning.getScoreboard());

        return dto;
    }


}
