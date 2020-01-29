package hilosDelJuego;

import interfazDelJuego.JVentanaGrafica;
import logicaJuego.Bomberman;
import logicaJuego.Mapa;
import logicaJuego.Bloque;

import java.util.ArrayList;

import hilosDelJuego.Bombita;
import logicaJuego.Bomba;

public class IA extends Thread {

	private Bomberman bomber;
	private Mapa mapa;
	private Bombita hiloBomba;
	private int posXVieja;
	private int posYVieja;
	private ArrayList<Bomba> bombas;
	private int cont;
	private AlejarseBomba hiloAlejarse;
	private boolean andando;

	public IA(JVentanaGrafica ventana) {
		this.bomber = ventana.getNuevo().getJugadoresLocal().get(1);
		this.mapa = ventana.getNuevo();
		cont = 0;
		andando = true;
		
	}

	public void run() {

		while (andando == true) {
			bombas = new ArrayList<Bomba>();
			bomber.setNombre("tomi");

			if (bomber.isVivo() == false) {
				bomber.setVelX(0);
				bomber.setVelY(0);
			}

			if (mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY()).esBomba()) {
				bombas.add(((Bomba) mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY())));

			}

			for (int i = -4; i < 5; i++) {

				if (i != 0) {
					if ((bomber.getPosX() + i) <= 9 && (bomber.getPosX() + i) >= 1) {
						if (mapa.getPosicionMapa(bomber.getPosX() + i, bomber.getPosY()).esBomba()) {
							if (((Bomba) mapa.getPosicionMapa(bomber.getPosX() + i, bomber.getPosY()))
									.getRango() >= Math.abs(i))
								bombas.add(((Bomba) mapa.getPosicionMapa(bomber.getPosX() + i, bomber.getPosY())));
						}
					}

					if ((bomber.getPosY() + i) <= 13 && (bomber.getPosY() + i) >= 1) {
						if (mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY() + i).esBomba()) {
							if (((Bomba) mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY() + i))
									.getRango() >= Math.abs(i))
								bombas.add(((Bomba) mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY() + i)));
						}
					}
				}
			}

			if (bombas.size() == 0)
				this.perseguir();

			for (int i = 0; i < bombas.size(); i++) {
				int posXVieja = bomber.getPosX();
				int posYVieja = bomber.getPosY();

				int x = bombas.get(i).getX();
				int y = bombas.get(i).getY();
				int xB = bomber.getPosX();
				int yB = bomber.getPosY();

//				int posiblesMovDer[][] = { { xB + 1, yB }, { xB + 2, yB }, { xB + 2, yB + 1 }, { xB + 2, yB - 1 } };
//				int posiblesMovIzq[][] = { { xB - 1, yB }, { xB - 2, yB }, { xB - 2, yB + 1 }, { xB - 2, yB - 1 } };
//				int posibleMovAbajo[][] = { { xB, yB + 1 }, { xB, yB + 2 }, { xB - 1, yB + 2 }, { xB + 1, yB + 2 } };
//				int posiblesMovArriba[][] = { { xB, yB - 1 }, { xB, yB - 2 }, { xB - 1, yB - 2 }, { xB + 1, yB - 2 } };
//				boolean banderin=false;

//				if (y == bomber.getPosY() && x == bomber.getPosX()) {
//					if (xB + 2 <= 9 && yB + 1 <= 13) {
//						if (mapa.getPosicionMapa(xB + 1, yB).esBloque() && mapa.getPosicionMapa(xB + 2, yB).esBloque()
//								&& mapa.getPosicionMapa(xB + 2, yB + 1).esBloque()) {
//
//							if (((Bloque) mapa.getPosicionMapa(xB + 1, yB)).queTipo().equals("transitable")
//									&& ((Bloque) mapa.getPosicionMapa(xB + 2, yB)).queTipo().equals("transitable")
//									&& ((Bloque) mapa.getPosicionMapa(xB + 2, yB + 1)).queTipo()
//											.equals("transitable")) {
//								this.moverse(2, 1, "der", "abajo");
//								banderin = true;
//							}
//						}
//					}
//
//					if (xB + 2 <= 9 && yB - 1 >= 0 && banderin == false) {
//						if (mapa.getPosicionMapa(xB + 1, yB).esBloque() && mapa.getPosicionMapa(xB + 2, yB).esBloque()
//								&& mapa.getPosicionMapa(xB + 2, yB - 1).esBloque()) {
//							if (((Bloque) mapa.getPosicionMapa(xB + 1, yB)).queTipo().equals("transitable")
//									&& ((Bloque) mapa.getPosicionMapa(xB + 2, yB)).queTipo().equals("transitable")
//									&& ((Bloque) mapa.getPosicionMapa(xB + 2, yB - 1)).queTipo()
//											.equals("transitable")) {
//								this.moverse(2, 1, "der", "arriba");
//								banderin = true;
//							}
//						}
//					}
//				}

				if (y == bomber.getPosY()) {
					if (mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY() - 1).esBloque())
						if (((Bloque) mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY() - 1)).queTipo()
								.equals("transitable"))
							bomber.setVelY(-bomber.getVelocidad());
						else {
							if (mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY() + 1).esBloque())
								if (((Bloque) mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY() + 1)).queTipo()
										.equals("transitable"))
									bomber.setVelY(+bomber.getVelocidad());

								else {
									if (bombas.get(i).getX() > bomber.getPosX()) {
										hiloAlejarse = new AlejarseBomba(bomber, bombas.get(i), "izq", this, mapa);
										hiloAlejarse.start();

									} else {
										hiloAlejarse = new AlejarseBomba(bomber, bombas.get(i), "der", this, mapa);
										hiloAlejarse.start();

									}

								}
						}
				}

				if (x == bomber.getPosX()) {
					if (mapa.getPosicionMapa(bomber.getPosX() - 1, bomber.getPosY()).esBloque())
						if (((Bloque) mapa.getPosicionMapa(bomber.getPosX() - 1, bomber.getPosY())).queTipo()
								.equals("transitable"))
							bomber.setVelX(-bomber.getVelocidad());

						else {
							if (mapa.getPosicionMapa(bomber.getPosX() + 1, bomber.getPosY()).esBloque())
								if (((Bloque) mapa.getPosicionMapa(bomber.getPosX() + 1, bomber.getPosY())).queTipo()
										.equals("transitable"))
									bomber.setVelX(+bomber.getVelocidad());
								else {
									if (bombas.get(i).getY() > bomber.getPosY()) {
										hiloAlejarse = new AlejarseBomba(bomber, bombas.get(i), "arriba", this, mapa);
										hiloAlejarse.start();

									} else {
										hiloAlejarse = new AlejarseBomba(bomber, bombas.get(i), "abajo", this, mapa);
										hiloAlejarse.start();

									}

								}
						}
				}

				try {

					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (bomber.getPosX() != posXVieja)
					bomber.setVelX(0);
				if (bomber.getPosY() != posYVieja)
					bomber.setVelY(0);
			}

			if (mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY() - 1).esBloque()) {// si hay fuego arriba
																							// para
				if (((Bloque) mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY() - 1)).queTipo().equals("fuego")
						|| ((Bloque) mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY() - 1)).queTipo()
								.equals("fuegoPiedra")) {
					bomber.setVelY(0);

				}
			}

			if (mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY() - 1).esBloque()) {
				if (((Bloque) mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY() - 1)).queTipo()
						.equals("piedra")) {
					// si choca con una piedra arriba :
					hiloBomba = new Bombita(bomber, mapa);
					hiloBomba.start();// pone una bomba
					posXVieja = bomber.getPosX();
					posYVieja = bomber.getPosY();
				}

			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public void moverse(int x, int y, String dir1, String dir2) {
		int xDeseada = bomber.getPosX() + x;
		int yDeseada = bomber.getPosY() + y;

		while (bomber.getPosY() != yDeseada && bomber.getPosX() != xDeseada) {

			switch (dir1) {
			case "arriba":
				while (bomber.getPosY() != yDeseada)
					bomber.setVelY(-bomber.getVelocidad());
				break;
			case "abajo":
				while (bomber.getPosY() != yDeseada)
					bomber.setVelY(+bomber.getVelocidad());
				break;
			case "izq":
				while (bomber.getPosX() != xDeseada)
					bomber.setVelX(-bomber.getVelocidad());
				break;
			case "der":
				while (bomber.getPosX() != xDeseada)
					bomber.setVelX(+bomber.getVelocidad());
				break;
			}
			;
			switch (dir2) {
			case "arriba":
				while (bomber.getPosY() != yDeseada)
					bomber.setVelY(-bomber.getVelocidad());
				break;
			case "abajo":
				while (bomber.getPosY() != yDeseada)
					bomber.setVelY(+bomber.getVelocidad());
				break;
			case "izq":
				while (bomber.getPosX() != xDeseada)
					bomber.setVelX(-bomber.getVelocidad());
				break;
			case "der":
				while (bomber.getPosX() != xDeseada)
					bomber.setVelX(+bomber.getVelocidad());
				break;
			}
			;

		}

	}

	public void perseguir() {
		Bomberman bomber2 = mapa.getJugadoresLocal().get(0);
		System.out.println("estoy persiguiendo");

		ArrayList<Bomba> bombasas = new ArrayList<Bomba>();
		
		bomber.setVelX(0);
		bomber.setVelY(0);

		double posYVieja=-111;
		while (bomber.getPosGrafY() != bomber2.getPosGrafY() && bombasas.isEmpty() == true
				&& posYVieja!=bomber.getPosGrafY()) {
			posYVieja=bomber.getPosGrafY();
			
			
			if (mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY()).esBomba()) {
				bombasas.add(((Bomba) mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY())));
			}

			for (int i = -4; i < 5; i++) {

				if (i != 0) {
					if ((bomber.getPosX() + i) <= 9 && (bomber.getPosX() + i) >= 1) {
						if (mapa.getPosicionMapa(bomber.getPosX() + i, bomber.getPosY()).esBomba()) {
							if (((Bomba) mapa.getPosicionMapa(bomber.getPosX() + i, bomber.getPosY()))
									.getRango() >= Math.abs(i))
								bombasas.add(((Bomba) mapa.getPosicionMapa(bomber.getPosX() + i, bomber.getPosY())));
						}
					}

					if ((bomber.getPosY() + i) <= 13 && (bomber.getPosY() + i) >= 1) {
						if (mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY() + i).esBomba()) {
							if (((Bomba) mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY() + i))
									.getRango() >= Math.abs(i))
								bombasas.add(((Bomba) mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY() + i)));
						}
					}

				}
			}
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			if (bomber.getPosGrafY() < bomber2.getPosGrafY()) {
				if (mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY() + 1).esBloque()) {
					if (((Bloque) mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY() + 1)).queTipo()
							.equals("transitable")) {
						bomber.setVelY(bomber.getVelocidad());
					}
					else {
						if(bomber.getPosX()==bomber2.getPosX()) {
							if(mapa.getPosicionMapa(bomber.getPosX()-1, bomber.getPosY()).esBloque()) {
								if(((Bloque)mapa.getPosicionMapa(bomber.getPosX()-1, bomber.getPosY())).queTipo().equals("transitable")) {
									if(mapa.getPosicionMapa(bomber.getPosX()-1, bomber.getPosY()+1).esBloque()) {
										if(((Bloque)mapa.getPosicionMapa(bomber.getPosX()-1, bomber.getPosY()+1)).queTipo().equals("transitable")){
											int posXviejarda=bomber.getPosX();
											int posYviejarda=bomber.getPosY();
											while(((posXviejarda*40) -(40)) <= (bomber.getPosX()*40) && ((posYviejarda*40)+40) >= (bomber.getPosY()*40) ) {
												if(posYviejarda!=bomber.getPosY()) {
													bomber.setVelX(0);
												}
												else
													bomber.setVelX(-bomber.getVelocidad());
												bomber.setVelY(bomber.getVelocidad());
												
												
												try {
													Thread.sleep(20);
												} catch (InterruptedException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											}
										}
									}
								}
							}
						else if(mapa.getPosicionMapa(bomber.getPosX()+1, bomber.getPosY()).esBloque()) {
								if(((Bloque)mapa.getPosicionMapa(bomber.getPosX()+1, bomber.getPosY())).queTipo().equals("transitable")) {
									if(mapa.getPosicionMapa(bomber.getPosX()+1, bomber.getPosY()+1).esBloque()) {
										if(((Bloque)mapa.getPosicionMapa(bomber.getPosX()+1, bomber.getPosY()+1)).queTipo().equals("transitable")){
											int posXviejarda=bomber.getPosX();
											int posYviejarda=bomber.getPosY();
											while(((posXviejarda*40) +(40)) >=(bomber.getPosX()*40) && ((posYviejarda*40)+40) >= (bomber.getPosY()*40) ) {
												if(posYviejarda!=bomber.getPosY()) {
													bomber.setVelX(0);
												}
												else
													bomber.setVelX(+bomber.getVelocidad());
												bomber.setVelY(bomber.getVelocidad());
												
												
												try {
													Thread.sleep(20);
												} catch (InterruptedException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}

			if (bomber.getPosGrafY() > bomber2.getPosGrafY()) {
				if (mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY() - 1).esBloque()) 
					if (((Bloque) mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY() - 1)).queTipo()
							.equals("transitable")) {
						bomber.setVelY(-bomber.getVelocidad());
					}
					else {
						if(bomber.getPosX()==bomber2.getPosX()) {
							if(mapa.getPosicionMapa(bomber.getPosX()-1, bomber.getPosY()).esBloque()) {
								if(((Bloque)mapa.getPosicionMapa(bomber.getPosX()-1, bomber.getPosY())).queTipo().equals("transitable")) {
									if(mapa.getPosicionMapa(bomber.getPosX()-1, bomber.getPosY()-1).esBloque()) {
										if(((Bloque)mapa.getPosicionMapa(bomber.getPosX()-1, bomber.getPosY()-1)).queTipo().equals("transitable")){
											int posXviejarda=bomber.getPosX();
											int posYviejarda=bomber.getPosY();
											while(((posXviejarda*40) -(40)) <= (bomber.getPosX()*40) && ((posYviejarda*40)-40) <= (bomber.getPosY()*40) ) {
												if(posYviejarda!=bomber.getPosY()) {
													bomber.setVelX(0);
												}
												else
													bomber.setVelX(-bomber.getVelocidad());
												bomber.setVelY(-bomber.getVelocidad());
												
												
												try {
													Thread.sleep(20);
												} catch (InterruptedException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											}
										}
									}
								}
							}
						else if(mapa.getPosicionMapa(bomber.getPosX()+1, bomber.getPosY()).esBloque()) {
								if(((Bloque)mapa.getPosicionMapa(bomber.getPosX()+1, bomber.getPosY())).queTipo().equals("transitable")) {
									if(mapa.getPosicionMapa(bomber.getPosX()+1, bomber.getPosY()-1).esBloque()) {
										if(((Bloque)mapa.getPosicionMapa(bomber.getPosX()+1, bomber.getPosY()-1)).queTipo().equals("transitable")){
											int posXviejarda=bomber.getPosX();
											int posYviejarda=bomber.getPosY();
											while(((posXviejarda*40) +(40)) >=(bomber.getPosX()*40) && ((posYviejarda*40)-40) <= (bomber.getPosY()*40) ) {
												if(posYviejarda!=bomber.getPosY()) {
													bomber.setVelX(0);
												}
												else
													bomber.setVelX(+bomber.getVelocidad());
												bomber.setVelY(-bomber.getVelocidad());
												
												
												try {
													Thread.sleep(20);
												} catch (InterruptedException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											}
										}
									}
								}
							}
						}
					}
					
			}
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}

		bomber.setVelX(0);
		bomber.setVelY(0);
		bombasas.clear();


		double posXVieja=-11;
		while (bomber.getPosGrafX() != bomber2.getPosGrafX() && bombasas.isEmpty() == true 
				&& posXVieja!=bomber.getPosGrafX()) {
			posXVieja=bomber.getPosGrafX();
			if (mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY()).esBomba()) {
				bombasas.add(((Bomba) mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY())));
			}

			for (int i = -4; i < 5; i++) {

				if (i != 0) {
					if ((bomber.getPosX() + i) <= 9 && (bomber.getPosX() + i) >= 1) {
						if (mapa.getPosicionMapa(bomber.getPosX() + i, bomber.getPosY()).esBomba()) {
							if (((Bomba) mapa.getPosicionMapa(bomber.getPosX() + i, bomber.getPosY()))
									.getRango() >= Math.abs(i))
								bombasas.add(((Bomba) mapa.getPosicionMapa(bomber.getPosX() + i, bomber.getPosY())));
						}
					}

					if ((bomber.getPosY() + i) <= 13 && (bomber.getPosY() + i) >= 1) {
						if (mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY() + i).esBomba()) {
							if (((Bomba) mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY() + i))
									.getRango() >= Math.abs(i))
								bombasas.add(((Bomba) mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY() + i)));
						}
					}

				}
			}
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (bomber.getPosGrafX() < bomber2.getPosGrafX()) {
				if (mapa.getPosicionMapa(bomber.getPosX() + 1, bomber.getPosY()).esBloque())
					if (((Bloque) mapa.getPosicionMapa(bomber.getPosX() + 1, bomber.getPosY())).queTipo()
							.equals("transitable")) {
						bomber.setVelX(bomber.getVelocidad());
					}
					else {
						if(bomber.getPosY()==bomber2.getPosY()) {
							if(mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY()-1).esBloque()) {
								if(((Bloque)mapa.getPosicionMapa(bomber.getPosX()+1, bomber.getPosY()-1)).queTipo().equals("transitable")) {
									if(mapa.getPosicionMapa(bomber.getPosX()+1, bomber.getPosY()-1).esBloque()) {
										if(((Bloque)mapa.getPosicionMapa(bomber.getPosX()+1, bomber.getPosY()-1)).queTipo().equals("transitable")){
											int posXviejarda=bomber.getPosX();
											int posYviejarda=bomber.getPosY();
											while(((posYviejarda*40) -(40)) <= (bomber.getPosY()*40) && ((posXviejarda*40)+40) >= (bomber.getPosX()*40) ) {
												if(posXviejarda!=bomber.getPosX()) {
													bomber.setVelY(0);
												}
												else
													bomber.setVelY(-bomber.getVelocidad());
												bomber.setVelX(bomber.getVelocidad());
												
												
												try {
													Thread.sleep(20);
												} catch (InterruptedException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											}
										}
									}
								}
							}
							else if(((Bloque)mapa.getPosicionMapa(bomber.getPosX()+1, bomber.getPosY()+1)).queTipo().equals("transitable")) {
								if(mapa.getPosicionMapa(bomber.getPosX()+1, bomber.getPosY()+1).esBloque()) {
									if(((Bloque)mapa.getPosicionMapa(bomber.getPosX()+1, bomber.getPosY()+1)).queTipo().equals("transitable")){
										int posXviejarda=bomber.getPosX();
										int posYviejarda=bomber.getPosY();
										while(((posYviejarda*40) +(40)) >= (bomber.getPosY()*40) && ((posXviejarda*40)+40) >= (bomber.getPosX()*40) ) {
											if(posXviejarda!=bomber.getPosX()) {
												bomber.setVelY(0);
											}
											else
												bomber.setVelY(+bomber.getVelocidad());
											bomber.setVelX(bomber.getVelocidad());
											
											
											try {
												Thread.sleep(20);
											} catch (InterruptedException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									}
								}
							}
						}
						
					}
					
			}

			if (bomber.getPosGrafX() > bomber2.getPosGrafX()) {
				if (mapa.getPosicionMapa(bomber.getPosX() - 1, bomber.getPosY()).esBloque()) {
					if (((Bloque) mapa.getPosicionMapa(bomber.getPosX() - 1, bomber.getPosY())).queTipo()
							.equals("transitable")) {
						bomber.setVelX(-bomber.getVelocidad());
					}
					else {
						if(bomber.getPosY()==bomber2.getPosY()) {
							if(mapa.getPosicionMapa(bomber.getPosX(), bomber.getPosY()-1).esBloque()) {
								if(((Bloque)mapa.getPosicionMapa(bomber.getPosX()-1, bomber.getPosY()-1)).queTipo().equals("transitable")) {
									if(mapa.getPosicionMapa(bomber.getPosX()-1, bomber.getPosY()-1).esBloque()) {
										if(((Bloque)mapa.getPosicionMapa(bomber.getPosX()-1, bomber.getPosY()-1)).queTipo().equals("transitable")){
											int posXviejarda=bomber.getPosX();
											int posYviejarda=bomber.getPosY();
											while(((posYviejarda*40) -(40)) <= (bomber.getPosY()*40) && ((posXviejarda*40)-40) <= (bomber.getPosX()*40) ) {
												if(posXviejarda!=bomber.getPosX()) {
													bomber.setVelY(0);
												}
												else
													bomber.setVelY(-bomber.getVelocidad());
												bomber.setVelX(-bomber.getVelocidad());
												
												
												try {
													Thread.sleep(20);
												} catch (InterruptedException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											}
										}
									}
								}
							}
							else if(((Bloque)mapa.getPosicionMapa(bomber.getPosX()-1, bomber.getPosY()+1)).queTipo().equals("transitable")) {
								if(mapa.getPosicionMapa(bomber.getPosX()-1, bomber.getPosY()+1).esBloque()) {
									if(((Bloque)mapa.getPosicionMapa(bomber.getPosX()-1, bomber.getPosY()+1)).queTipo().equals("transitable")){
										int posXviejarda=bomber.getPosX();
										int posYviejarda=bomber.getPosY();
										while(((posYviejarda*40) +(40)) >= (bomber.getPosY()*40) && ((posXviejarda*40)-40) <= (bomber.getPosX()*40) ) {
											if(posXviejarda!=bomber.getPosX()) {
												bomber.setVelY(0);
											}
											else
												bomber.setVelY(+bomber.getVelocidad());
											bomber.setVelX(-bomber.getVelocidad());
											
											
											try {
												Thread.sleep(20);
											} catch (InterruptedException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									}
								}
							}
						}
						
					}
				}
			}
			try {
				Thread.sleep(20);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		bomber.setVelX(0);
		bomber.setVelY(0);
	}

	public boolean isAndando() {
		return andando;
	}

	public void setAndando(boolean andando) {
		this.andando = andando;
	}

}
	