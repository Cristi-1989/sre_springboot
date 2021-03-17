export class Movie {
  id: number;
  name: string;
  description: string;

    static fromHttp(movie : Movie) {
      const newMovie = new Movie();
      newMovie.id = movie.id;
      newMovie.description = movie.description;
      newMovie.name = movie.name;
      return newMovie;
    }

}
