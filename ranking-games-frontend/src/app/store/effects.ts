import { Injectable } from "@angular/core";
import { Actions, Effect, ofType } from "@ngrx/effects";
import { EMPTY } from "rxjs";
import { catchError, map, mergeMap } from "rxjs/operators";
import { PlayerActionTypes } from "./player.actions";
import { PlayerService } from "../player/player.service";

@Injectable()
export class PlayerEffects {
  @Effect()
  loadPlayers$ = this.actions$.pipe(
    ofType(PlayerActionTypes.PLAYER_ALL),
    mergeMap(() =>
      this.playerService.getAll().pipe(
        map(players => {
          console.log(players);
          return {
            type: PlayerActionTypes.LOAD_PLAYER_SUCCESS,
            payload: players
          };
        }),
        catchError(() => EMPTY)
      )
    )
  );

  constructor(
    private actions$: Actions,
    private playerService: PlayerService
  ) {}
}
