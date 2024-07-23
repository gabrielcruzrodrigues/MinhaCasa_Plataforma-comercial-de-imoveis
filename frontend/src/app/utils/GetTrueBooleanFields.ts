import { BodyBooleanFields } from "../interfaces/BodyBooleanFields";

export class GetTrueBooleanFields {

     static booleanFields: (keyof BodyBooleanFields)[] = [
          'gatedCommunity', 'videos', 'beach', 'disabledAccess', 'playground', 'grill',
          'energyGenerator', 'closeToTheCenter', 'elevator', 'pool', 'frontDesk',
          'multiSportsCourt', 'gym', 'steamRoom', 'cableTV', 'heating', 'cabinetsInTheKitchen',
          'bathroomInTheRoom', 'internet', 'partyRoom', 'airConditioning', 'americanKitchen',
          'hydromassage', 'fireplace', 'privatePool', 'electronicGate', 'serviceArea', 'pub',
          'closet', 'office', 'yard', 'alarmSystem', 'balcony', 'concierge24Hour', 'walledArea',
          'dogAllowed', 'catAllowed', 'cameras', 'furnished', 'seaView'
     ];
      
     static translations: { [key in keyof BodyBooleanFields]: string } = {
          gatedCommunity: 'Condomínio Fechado',
          videos: 'Vídeos',
          beach: 'Praia',
          disabledAccess: 'Acesso para Deficientes',
          playground: 'Playground',
          grill: 'Churrasqueira',
          energyGenerator: 'Gerador de Energia',
          closeToTheCenter: 'Perto do Centro',
          elevator: 'Elevador',
          pool: 'Piscina',
          frontDesk: 'Recepção',
          multiSportsCourt: 'Quadra Poliesportiva',
          gym: 'Academia',
          steamRoom: 'Sauna',
          cableTV: 'TV a Cabo',
          heating: 'Aquecimento',
          cabinetsInTheKitchen: 'Armários na Cozinha',
          bathroomInTheRoom: 'Banheiro na Suíte',
          internet: 'Internet',
          partyRoom: 'Salão de Festas',
          airConditioning: 'Ar-Condicionado',
          americanKitchen: 'Cozinha Americana',
          hydromassage: 'Hidromassagem',
          fireplace: 'Lareira',
          privatePool: 'Piscina Privada',
          electronicGate: 'Portão Eletrônico',
          serviceArea: 'Área de Serviço',
          pub: 'Pub',
          closet: 'Closet',
          office: 'Escritório',
          yard: 'Quintal',
          alarmSystem: 'Sistema de Alarme',
          balcony: 'Varanda',
          concierge24Hour: 'Portaria 24h',
          walledArea: 'Área Murada',
          dogAllowed: 'Permite Cachorro',
          catAllowed: 'Permite Gato',
          cameras: 'Câmeras de Segurança',
          furnished: 'Mobiliado',
          seaView: 'Vista para o Mar'
     };

     static get(body: any): string[] {
          const trueFields = this.booleanFields.filter(field => body[field] === true);
          return trueFields.map(field => this.translations[field]);
     }
}