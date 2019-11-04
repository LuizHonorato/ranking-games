import * as playerActions from "./players.actions";
import { AppAction } from "../../app.action";
import { createFeatureSelector, createSelector } from "@ngrx/store";
import { Player } from "../shared/player";

export interface State {
  data: Player[];
  selected: Player;
  action: string;
  done: boolean;
  error?: Error;
}

const initialState: State = {
  data: [],
  selected: null,
  action: null,
  done: false,
  error: null
};

export function reducer(state = initialState, action: AppAction): State {
  switch (action.type) {
    case playerActions.GET_PLAYERS:
      return {
        ...state,
        action: playerActions.GET_PLAYERS,
        done: false,
        selected: null,
        error: null
      };
    case playerActions.GET_PLAYERS_SUCCESS:
      return {
        ...state,
        data: action.payload,
        done: true,
        selected: null,
        error: null
      };
    case playerActions.GET_PLAYERS_ERROR:
      return {
        ...state,
        done: true,
        selected: null,
        error: action.payload
      };

    case playerActions.GET_PLAYER:
      return {
        ...state,
        action: playerActions.GET_PLAYER,
        done: false,
        selected: null,
        error: null
      };
    case playerActions.GET_PLAYER_SUCCESS:
      return {
        ...state,
        selected: action.payload,
        done: true,
        error: null
      };
    case playerActions.GET_PLAYER_ERROR:
      return {
        ...state,
        selected: null,
        done: true,
        error: action.payload
      };

    case playerActions.CREATE_PLAYER:
      return {
        ...state,
        selected: action.payload,
        action: playerActions.CREATE_PLAYER,
        done: false,
        error: null
      };
    case playerActions.CREATE_PLAYER_SUCCESS: {
      const newPlayer = {
        ...state.selected,
        id: action.payload
      };
      const data = [...state.data, newPlayer];
      return {
        ...state,
        data,
        selected: null,
        error: null,
        done: true
      };
    }
    case playerActions.CREATE_PLAYER_ERROR:
      return {
        ...state,
        selected: null,
        done: true,
        error: action.payload
      };

    case playerActions.UPDATE_PLAYER:
      return {
        ...state,
        selected: action.payload,
        action: playerActions.UPDATE_PLAYER,
        done: false,
        error: null
      };
    case playerActions.UPDATE_PLAYER_SUCCESS: {
      const index = state.data.findIndex(h => h.id === state.selected.id);
      if (index >= 0) {
        const data = [
          ...state.data.slice(0, index),
          state.selected,
          ...state.data.slice(index + 1)
        ];
        return {
          ...state,
          data,
          done: true,
          selected: null,
          error: null
        };
      }
      return state;
    }
    case playerActions.UPDATE_PLAYER_ERROR:
      return {
        ...state,
        done: true,
        selected: null,
        error: action.payload
      };

    case playerActions.DELETE_PLAYER: {
      const selected = state.data.find(h => h.id === action.payload);
      return {
        ...state,
        selected,
        action: playerActions.DELETE_PLAYER,
        done: false,
        error: null
      };
    }
    case playerActions.DELETE_PLAYER_SUCCESS: {
      const data = state.data.filter(h => h.id !== state.selected.id);
      return {
        ...state,
        data,
        selected: null,
        error: null,
        done: true
      };
    }
    case playerActions.DELETE_PLAYER_ERROR:
      return {
        ...state,
        selected: null,
        done: true,
        error: action.payload
      };
  }
  return state;
}

//SELETORES

export const getPlayersState = createFeatureSelector<State>("players");
export const getAllPlayers = createSelector(
  getPlayersState,
  (state: State) => state.data
);
export const getPlayer = createSelector(
  getPlayersState,
  (state: State) => {
    if (state.action === playerActions.GET_PLAYER && state.done) {
      return state.selected;
    } else {
      return null;
    }
  }
);
export const isDeleted = createSelector(
  getPlayersState,
  (state: State) =>
    state.action === playerActions.DELETE_PLAYER && state.done && !state.error
);
export const isCreated = createSelector(
  getPlayersState,
  (state: State) =>
    state.action === playerActions.CREATE_PLAYER && state.done && !state.error
);
export const isUpdated = createSelector(
  getPlayersState,
  (state: State) =>
    state.action === playerActions.UPDATE_PLAYER && state.done && !state.error
);

export const getDeleteError = createSelector(
  getPlayersState,
  (state: State) => {
    return state.action === playerActions.DELETE_PLAYER ? state.error : null;
  }
);
export const getCreateError = createSelector(
  getPlayersState,
  (state: State) => {
    return state.action === playerActions.CREATE_PLAYER ? state.error : null;
  }
);
export const getUpdateError = createSelector(
  getPlayersState,
  (state: State) => {
    return state.action === playerActions.UPDATE_PLAYER ? state.error : null;
  }
);
export const getPlayersError = createSelector(
  getPlayersState,
  (state: State) => {
    return state.action === playerActions.GET_PLAYERS ? state.error : null;
  }
);
export const getPlayerError = createSelector(
  getPlayersState,
  (state: State) => {
    return state.action === playerActions.GET_PLAYER ? state.error : null;
  }
);
