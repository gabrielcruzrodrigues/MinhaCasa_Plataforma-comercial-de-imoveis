export class ConverterAgeToPortuguese {
     
     static converter(age: string): string {
          switch(age) {
               case 'IN_THE_PLANT':
                    return 'na planta';
               case 'BUILDING':
                    return 'construindo';
               case 'UP_TO_1_YEARS':
                    return 'mais ou menos 1 ano';
               case 'UP_TO_2_YEARS':
                    return 'mais de 2 anos';
               case 'UP_TO_5_YEARS':
                    return 'mais de 5 anos';
               case 'UP_TO_10_YEARS':
                    return 'mais de 10 anos';
               case 'UP_TO_20_YEARS':
                    return 'mais de 20 anos';
               case 'UP_TO_30_YEARS':
                    return 'mais de 30 anos';
               case 'UP_TO_40_YEARS':
                    return 'mais de 40 anos';
               case 'OVER_50_YEARS':
                    return 'mais de 50 anos';
               default:
                    return 'idade não definida';
          }
     }
}