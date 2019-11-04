import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { Player } from "./player";

@Injectable()
export class PlayersService {
  protected URL = "http://localhost:8080/jogadores";

  constructor(protected http: HttpClient) {}

  public findById(id: any): Observable<Player> {
    return this.http.get<Player>(this.URL + "/" + id);
  }

  public findAll(params?): Observable<Player[]> {
    return this.http.get<Player[]>(this.URL, { params });
  }

  public delete(id): Observable<Player> {
    return this.http.delete<Player>(this.URL + "/" + id);
  }

  public insert(data: Player): Observable<Player> {
    let headers = new HttpHeaders();
    headers = headers.set("Content-Type", "application/json; charset=utf-8");

    return this.http.post<Player>(this.URL, data, { headers });
  }

  public update(player: Player): Observable<Player> {
    let headers = new HttpHeaders();
    headers = headers.set("Content-Type", "application/json; charset=utf-8");

    return this.http.put<Player>(this.URL + "/" + player.id, player, {
      headers
    });
  }
}
