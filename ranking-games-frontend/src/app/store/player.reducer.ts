import * as fromPlayerActions from "./player.actions";

export const initialState = {
  players: []
};

export function PlayerReducer(
  state = initialState,
  action: fromPlayerActions.PlayerActions
) {
  console.log(action.type);
  switch (action.type) {
    case fromPlayerActions.PlayerActionTypes.LOAD_PLAYER_SUCCESS:
      return {
        ...state,
        players: [...action.payload]
      };

    case fromPlayerActions.PlayerActionTypes.PLAYER_NEW:
      return {
        ...state,
        players: [...state.players, action.payload]
      };

    case fromPlayerActions.PlayerActionTypes.PLAYER_UPDATE:
      return state;

    case fromPlayerActions.PlayerActionTypes.PLAYER_DELETE:
      return state;

    default:
      return state;
  }
}
