import { Injectable } from '@angular/core';
import { Oferta } from '../models/oferta.model';

@Injectable({
  providedIn: 'root'
})

export class OfertasService {

        public oferta: Array<Oferta> = [
                        {
                                id: 0,
                                categoria: "restaurante",
                                titulo: "Super Burger",
                                descricao_oferta: "Rodízio de Mini-hambúrger com opção de entrada.",
                                anunciante: "Original Burger",
                                valor: 29.90,
                                destaque: true,
                                imagens: [
                                        {url: "/assets/ofertas/imagem_1/img_hambuer.jpg"},
                                         {url: "/assets/ofertas/imagem_1/img2.jpg"},
                                         {url: "/assets/ofertas/imagem_1/img3.jpg"},
                                         {url: "/assets/ofertas/imagem_1/img4.jpg"}
                                ]
                        },
                        {
                                id: 1,
                                categoria: "restaurante",
                                titulo: "Cozinha Mexicana",
                                descricao_oferta: "Almoço ou Jantar com Rodízio Mexicano delicioso.",
                                anunciante: "Mexicana",
                                valor: 32.90,
                                destaque: true,
                                imagens: [
                                        {url: "/assets/ofertas/2/img1.jpg"},
                                        {url: "/assets/ofertas/2/img2.jpg"},
                                        {url: "/assets/ofertas/2/img3.jpg"},
                                        {url: "/assets/ofertas/2/img4.jpg"}
                                ]
                        
                        },
                        {
                                id: 2,
                                categoria: "diversao",
                                titulo: "Estância das águas",
                                descricao_oferta: "Diversão garantida com piscinas, trilhas e muito mais.",
                                anunciante: "Estância das águas",
                                valor: 31.90,
                                destaque: true,
                                imagens: [
                                        {url: "/assets/ofertas/3/img1.jpg"},
                                        {url: "/assets/ofertas/3/img2.jpg"},
                                        {url: "/assets/ofertas/3/img3.jpg"},
                                        {url: "/assets/ofertas/3/img4.jpg"},
                                        {url: "/assets/ofertas/3/img5.jpg"},
                                        {url: "/assets/ofertas/3/img6.jpg"}
                                ]
                        }
                ]
      

        public getOfertas(): Array<Oferta>{
                return this.oferta;
        }
        
        // TODO - melhoria
        public getOfertas_2(): Promise<Oferta[]>{
                return new Promise( ( resolve, reject ) => {  // A promisse tem um objeto que usa 2 funções " resolve, reject "                        
                        let deu_certo = true
                        if( deu_certo){
                                setTimeout( () => resolve( this.oferta ), 3000)                                
                        } else {
                                reject({ codigo_erro: 404, mensagem_erro: "Servidor não encontrado" })
                        }                                
                })
        }


  constructor() { }
}
