export enum Genre {
  ROCK = 'Rock',
  HUNNUROCK = 'Hunnu Rock',
  METAL = 'Metal',
  TECHNICALDEATHCORE = 'Technical Deathcore',
  MELODICDEATH = 'Melodic Death',
  PROGRESSIVEMELODICDEATH = 'Progressive Melodic Death',
  CLASSICAL = 'Classical',
  ELECTRO = 'Electro',
  POP = 'Pop',
  RAP = 'Rap',
  RNB = 'RnB',
  VARIETEFRANCAISE = 'Variete Francaise',
}

export const GenreLabelMapping: Record<Genre, string> = {
  [Genre.ROCK]: 'Rock',
  [Genre.HUNNUROCK]: 'Hunnu Rock',
  [Genre.METAL]: 'Metal',
  [Genre.TECHNICALDEATHCORE]: 'Technical Deathcore',
  [Genre.MELODICDEATH]: 'Melodic Death',
  [Genre.PROGRESSIVEMELODICDEATH]: 'Progressive Melodic Death',
  [Genre.CLASSICAL]: 'Classical',
  [Genre.ELECTRO]: 'Electro',
  [Genre.POP]: 'Pop',
  [Genre.RAP]: 'Rap',
  [Genre.RNB]: 'RnB',
  [Genre.VARIETEFRANCAISE]: 'Variete Francaise',
};
