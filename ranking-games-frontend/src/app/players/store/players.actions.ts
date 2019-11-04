import { Action } from "@ngrx/store";
import { Player } from "../shared/player";

export const GET_PLAYERS = "[ALL] Players";
export const GET_PLAYERS_SUCCESS = "[ALL] Players Success";
export const GET_PLAYERS_ERROR = "[ALL] Players Error";

export const GET_PLAYER = "[GET] Player";
export const GET_PLAYER_SUCCESS = "[GET] Player Success";
export const GET_PLAYER_ERROR = "[GET] Player Error";

export const CREATE_PLAYER = "[CREATE] Player";
export const CREATE_PLAYER_SUCCESS = "[CREATE] Player Success";
export const CREATE_PLAYER_ERROR = "[CREATE] Player Error";

export const DELETE_PLAYER = "[DELETE] Player";
export const DELETE_PLAYER_SUCCESS = "[DELETE] Player Success";
export const DELETE_PLAYER_ERROR = "[DELETE] Player Error";

export const UPDATE_PLAYER = "[UPDATE] Player";
export const UPDATE_PLAYER_SUCCESS = "[UPDATE] Player Success";
export const UPDATE_PLAYER_ERROR = "[UPDATE] Player Error";

export class GetAllPlayers implements Action {
  readonly type = GET_PLAYERS;
}

export class GetAllPlayersSuccess implements Action {
  readonly type = GET_PLAYERS_SUCCESS;
  constructor(public payload: Player[]) {}
}

export class GetAllPlayersError implements Action {
  readonly type = GET_PLAYERS_ERROR;

  constructor(public payload: Error) {}
}

export class GetPlayer implements Action {
  readonly type = GET_PLAYER;

  constructor(public payload: number) {}
}

export class GetPlayerSuccess implements Action {
  readonly type = GET_PLAYER_SUCCESS;

  constructor(public payload: Player) {}
}

export class GetPlayerError implements Action {
  readonly type = GET_PLAYER_ERROR;

  constructor(public payload: Error) {}
}

export class AddPlayer implements Action {
  readonly type = CREATE_PLAYER;

  constructor(public payload: Player) {}
}

export class AddPlayerSuccess implements Action {
  readonly type = CREATE_PLAYER_SUCCESS;

  constructor(public payload: number) {}
}

export class AddPlayerError implements Action {
  readonly type = CREATE_PLAYER_ERROR;

  constructor(public payload: Error) {}
}

export class UpdatePlayer implements Action {
  readonly type = UPDATE_PLAYER;

  constructor(public payload: Player) {}
}

export class UpdatePlayerSuccess implements Action {
  readonly type = UPDATE_PLAYER_SUCCESS;
}

export class UpdatePlayerError implements Action {
  readonly type = UPDATE_PLAYER_ERROR;

  constructor(public payload: Error) {}
}

export class RemovePlayer implements Action {
  readonly type = DELETE_PLAYER;

  constructor(public payload: number) {}
}

export class RemovePlayerSuccess implements Action {
  readonly type = DELETE_PLAYER_SUCCESS;

  constructor(public payload: Player) {}
}

export class RemovePlayerError implements Action {
  readonly type = DELETE_PLAYER_ERROR;

  constructor(public payload: Error) {}
}
