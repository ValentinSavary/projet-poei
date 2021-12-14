export enum Genre {
  ROCK = 'Rock',
  METAL = 'Metal',
  TECHNICALDEATHCORE = 'Technical Deathcore',
  CLASSICAL = 'Classical',
  POP = 'Pop',
  RAP = 'Rap',
  RNB = 'RnB',
  VARIETEFRANCAISE = 'Variete Francaise',
}

export const GenreLabelMapping: Record<Genre, string> = {
  [Genre.ROCK]: 'Rock',
  [Genre.METAL]: 'Metal',
  [Genre.TECHNICALDEATHCORE]: 'Technical Deathcore',
  [Genre.CLASSICAL]: 'Classical',
  [Genre.POP]: 'Pop',
  [Genre.RAP]: 'Rap',
  [Genre.RNB]: 'RnB',
  [Genre.VARIETEFRANCAISE]: 'Variete Francaise',
};
