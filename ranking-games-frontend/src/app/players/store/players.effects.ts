import { Injectable } from "@angular/core";
import { Actions, Effect, ofType } from "@ngrx/effects";
import * as playerActions from "./players.actions";
import {
  AddPlayer,
  AddPlayerError,
  AddPlayerSuccess,
  GetAllPlayersSuccess,
  GetPlayer,
  GetPlayerError,
  GetPlayerSuccess,
  RemovePlayer,
  RemovePlayerError,
  RemovePlayerSuccess,
  UpdatePlayer,
  UpdatePlayerError,
  UpdatePlayerSuccess
} from "./players.actions";
import { Observable } from "rxjs";
import { Action } from "@ngrx/store";
import { PlayersService } from "../shared/players.service";
import { Player } from "../shared/player";
import { catchError, map, switchMap } from "rxjs/operators";

@Injectable()
export class PlayerEffects {
  constructor(private actions$: Actions, private svc: PlayersService) {}

  @Effect()
  getAllPlayers$: Observable<Action> = this.actions$.pipe(
    ofType(playerActions.GET_PLAYERS),
    switchMap(() => this.svc.findAll()),
    map(heroes => new GetAllPlayersSuccess(heroes)),
    catchError(err => [new GetPlayerError(err)])
  );

  @Effect()
  getPlayer$ = this.actions$.pipe(
    ofType(playerActions.GET_PLAYER),
    map((action: GetPlayer) => action.payload),
    switchMap(id => this.svc.findById(id)),
    map(hero => new GetPlayerSuccess(hero)),
    catchError(err => [new GetPlayerError(err)])
  );

  @Effect()
  updatePlayer$ = this.actions$.pipe(
    ofType(playerActions.UPDATE_PLAYER),
    map((action: UpdatePlayer) => action.payload),
    switchMap(player => this.svc.update(player)),
    map(() => new UpdatePlayerSuccess()),
    catchError(err => [new UpdatePlayerError(err)])
  );

  @Effect()
  createPlayer$ = this.actions$.pipe(
    ofType(playerActions.CREATE_PLAYER),
    map((action: AddPlayer) => action.payload),
    switchMap(newPlayer => this.svc.insert(newPlayer)),
    map(response => new AddPlayerSuccess(response.id)),
    catchError(err => [new AddPlayerError(err)])
  );

  @Effect()
  removePlayer$ = this.actions$.pipe(
    ofType(playerActions.DELETE_PLAYER),
    map((action: RemovePlayer) => action.payload),
    switchMap(id => this.svc.delete(id)),
    map((hero: Player) => new RemovePlayerSuccess(hero)),
    catchError(err => [new RemovePlayerError(err)])
  );
}
