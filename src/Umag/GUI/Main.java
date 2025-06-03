package Umag.GUI;

import Umag.Circuito.Circuito;
import Umag.Componentes.Componente;
import Umag.Componentes.Led;
import Umag.Componentes.Switch;
import Umag.Compuertas.CompuertaAND;
import Umag.Compuertas.CompuertaNOT;
import Umag.Compuertas.CompuertaOR;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;



        

public class Main extends javax.swing.JFrame {
    private MiPanel mp;
    private Circuito circuito;
    private Componente componenteSeleccionado;
    int xMouse, yMouse;

    
    public Main() {
       
        initComponents();
        circuito = new Circuito("Circuito 1"); 
 
        panelTablero.setLayout(new BorderLayout()); 
        mp = new MiPanel();
        mp.setCircuito(circuito);
        circuito.setPanelReferencia(mp);

        panelTablero.add(mp, BorderLayout.CENTER); 
        panelTablero.revalidate();
        panelTablero.repaint();

        mp.setBorder(BorderFactory.createEtchedBorder());
        mp.setBackground(Color.white);
        btnEncenderSW = new javax.swing.JToggleButton();
        btnEncenderSW.setText("Toggle Switch");
        btnEncenderSW.setEnabled(false);
   
    }
    
    public void actualizarControlesEntrada(Componente componente) {
             this.componenteSeleccionado = componente;
        btnEncenderSW.setEnabled(componente instanceof Switch);
        if (componente instanceof Switch aSwitch) {
            btnEncenderSW.setSelected(aSwitch.getEstado());
        }
    }
    
    public void limpiarSeleccion() {
        componenteSeleccionado = null;
        btnEncenderSW.setEnabled(false);
        mp.setComponenteSeleccionado(null); 
        mp.setConectorSeleccionado(null);   
        mp.repaint(); 
    }
  

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Background = new javax.swing.JPanel();
        panelTablero = new javax.swing.JPanel();
        panelSuperior = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JLabel();
        btnEncenderSW = new javax.swing.JToggleButton();
        panelHerramientas = new javax.swing.JPanel();
        btnAND = new javax.swing.JLabel();
        btnOR = new javax.swing.JLabel();
        btnNOT = new javax.swing.JLabel();
        btnClear = new javax.swing.JLabel();
        btnConectar = new javax.swing.JLabel();
        btnLED = new javax.swing.JLabel();
        btnSwitch = new javax.swing.JLabel();
        btnSeleccionar = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        subBtnAbrir = new javax.swing.JMenuItem();
        subBtnNuevo = new javax.swing.JMenuItem();
        subBtnGuardar = new javax.swing.JMenuItem();
        subBtnGuardarComo = new javax.swing.JMenuItem();
        subBtnSalir = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        subBtnAcercade = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);

        Background.setBackground(new java.awt.Color(255, 255, 255));
        Background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelTablero.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelTableroLayout = new javax.swing.GroupLayout(panelTablero);
        panelTablero.setLayout(panelTableroLayout);
        panelTableroLayout.setHorizontalGroup(
            panelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );
        panelTableroLayout.setVerticalGroup(
            panelTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
        );

        Background.add(panelTablero, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 650, 560));

        panelSuperior.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelSuperiorMouseDragged(evt);
            }
        });
        panelSuperior.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelSuperiorMousePressed(evt);
            }
        });

        btnCerrar.setBackground(new java.awt.Color(204, 204, 204));
        btnCerrar.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 24)); // NOI18N
        btnCerrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCerrar.setText("X");
        btnCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCerrar.setOpaque(true);
        btnCerrar.setPreferredSize(new java.awt.Dimension(40, 40));
        btnCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCerrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCerrarMouseExited(evt);
            }
        });

        btnEncenderSW.setText("CAMBIAR");
        btnEncenderSW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEncenderSWActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSuperiorLayout = new javax.swing.GroupLayout(panelSuperior);
        panelSuperior.setLayout(panelSuperiorLayout);
        panelSuperiorLayout.setHorizontalGroup(
            panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEncenderSW)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 672, Short.MAX_VALUE)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelSuperiorLayout.setVerticalGroup(
            panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuperiorLayout.createSequentialGroup()
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEncenderSW, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Background.add(panelSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 40));

        panelHerramientas.setBackground(new java.awt.Color(255, 255, 204));

        btnAND.setBackground(new java.awt.Color(204, 204, 204));
        btnAND.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAND.setIcon(new javax.swing.ImageIcon(getClass().getResource("/umag/recursos/imagen/AND.png"))); // NOI18N
        btnAND.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAND.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAND.setOpaque(true);
        btnAND.setPreferredSize(new java.awt.Dimension(100, 100));
        btnAND.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnANDMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnANDMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnANDMouseExited(evt);
            }
        });

        btnOR.setBackground(new java.awt.Color(204, 204, 204));
        btnOR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnOR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/umag/recursos/imagen/OR.png"))); // NOI18N
        btnOR.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnOR.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOR.setOpaque(true);
        btnOR.setPreferredSize(new java.awt.Dimension(100, 100));
        btnOR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnORMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnORMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnORMouseExited(evt);
            }
        });

        btnNOT.setBackground(new java.awt.Color(204, 204, 204));
        btnNOT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnNOT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/umag/recursos/imagen/NOT.png"))); // NOI18N
        btnNOT.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnNOT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNOT.setOpaque(true);
        btnNOT.setPreferredSize(new java.awt.Dimension(100, 100));
        btnNOT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNOTMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNOTMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNOTMouseExited(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(204, 204, 204));
        btnClear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Umag/recursos/imagen/Clear.png"))); // NOI18N
        btnClear.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnClear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClear.setOpaque(true);
        btnClear.setPreferredSize(new java.awt.Dimension(100, 50));
        btnClear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnClearMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnClearMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnClearMouseExited(evt);
            }
        });

        btnConectar.setBackground(new java.awt.Color(204, 204, 204));
        btnConectar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnConectar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Umag/recursos/imagen/Conectar.png"))); // NOI18N
        btnConectar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnConectar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConectar.setOpaque(true);
        btnConectar.setPreferredSize(new java.awt.Dimension(100, 50));
        btnConectar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnConectarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConectarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConectarMouseExited(evt);
            }
        });

        btnLED.setBackground(new java.awt.Color(204, 204, 204));
        btnLED.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnLED.setIcon(new javax.swing.ImageIcon(getClass().getResource("/umag/recursos/imagen/LED.png"))); // NOI18N
        btnLED.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnLED.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLED.setOpaque(true);
        btnLED.setPreferredSize(new java.awt.Dimension(100, 100));
        btnLED.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLEDMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLEDMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLEDMouseExited(evt);
            }
        });

        btnSwitch.setBackground(new java.awt.Color(204, 204, 204));
        btnSwitch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSwitch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/umag/recursos/imagen/Switch.png"))); // NOI18N
        btnSwitch.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSwitch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSwitch.setOpaque(true);
        btnSwitch.setPreferredSize(new java.awt.Dimension(100, 100));
        btnSwitch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSwitchMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSwitchMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSwitchMouseExited(evt);
            }
        });

        btnSeleccionar.setBackground(new java.awt.Color(204, 204, 204));
        btnSeleccionar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSeleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/umag/recursos/imagen/Seleccionar.png"))); // NOI18N
        btnSeleccionar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSeleccionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSeleccionar.setOpaque(true);
        btnSeleccionar.setPreferredSize(new java.awt.Dimension(100, 100));
        btnSeleccionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSeleccionarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSeleccionarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSeleccionarMouseExited(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(204, 204, 204));
        btnEliminar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Umag/recursos/imagen/Eliminar.png"))); // NOI18N
        btnEliminar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.setOpaque(true);
        btnEliminar.setPreferredSize(new java.awt.Dimension(100, 50));
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelHerramientasLayout = new javax.swing.GroupLayout(panelHerramientas);
        panelHerramientas.setLayout(panelHerramientasLayout);
        panelHerramientasLayout.setHorizontalGroup(
            panelHerramientasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHerramientasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelHerramientasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelHerramientasLayout.createSequentialGroup()
                        .addGroup(panelHerramientasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelHerramientasLayout.createSequentialGroup()
                                .addComponent(btnNOT, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLED, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelHerramientasLayout.createSequentialGroup()
                                .addComponent(btnAND, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnOR, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHerramientasLayout.createSequentialGroup()
                        .addGroup(panelHerramientasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelHerramientasLayout.createSequentialGroup()
                                .addComponent(btnSwitch, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelHerramientasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConectar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(16, 16, 16))))
        );
        panelHerramientasLayout.setVerticalGroup(
            panelHerramientasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHerramientasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelHerramientasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAND, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOR, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelHerramientasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnNOT, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLED, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelHerramientasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSwitch, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelHerramientasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnConectar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(214, Short.MAX_VALUE))
        );

        Background.add(panelHerramientas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 160, 560));

        jMenu1.setText("File");

        subBtnAbrir.setText("Abrir");
        subBtnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subBtnAbrirActionPerformed(evt);
            }
        });
        jMenu1.add(subBtnAbrir);

        subBtnNuevo.setText("Nuevo");
        subBtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subBtnNuevoActionPerformed(evt);
            }
        });
        jMenu1.add(subBtnNuevo);

        subBtnGuardar.setText("Guardar");
        subBtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subBtnGuardarActionPerformed(evt);
            }
        });
        jMenu1.add(subBtnGuardar);

        subBtnGuardarComo.setText("Guardar como");
        subBtnGuardarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subBtnGuardarComoActionPerformed(evt);
            }
        });
        jMenu1.add(subBtnGuardarComo);

        subBtnSalir.setText("Salir");
        subBtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subBtnSalirActionPerformed(evt);
            }
        });
        jMenu1.add(subBtnSalir);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Ayuda");

        subBtnAcercade.setText("Acerca de...");
        subBtnAcercade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subBtnAcercadeActionPerformed(evt);
            }
        });
        jMenu3.add(subBtnAcercade);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnANDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnANDMouseClicked
        circuito.agregarComponente(new CompuertaAND(200, 50));
        mp.repaint();
    }//GEN-LAST:event_btnANDMouseClicked

    private void btnORMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnORMouseClicked
        circuito.agregarComponente(new CompuertaOR(200, 50));
        mp.repaint();
    }//GEN-LAST:event_btnORMouseClicked

    private void btnANDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnANDMouseEntered
        btnAND.setBackground(new Color(180, 180, 180));
    }//GEN-LAST:event_btnANDMouseEntered

    private void btnANDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnANDMouseExited
        btnAND.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_btnANDMouseExited

    private void btnNOTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNOTMouseClicked
        circuito.agregarComponente(new CompuertaNOT(200, 50));
        mp.repaint();
    }//GEN-LAST:event_btnNOTMouseClicked

    private void btnORMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnORMouseEntered
        btnOR.setBackground(new Color(180, 180, 180));
    }//GEN-LAST:event_btnORMouseEntered

    private void btnORMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnORMouseExited
        btnOR.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_btnORMouseExited

    private void btnNOTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNOTMouseEntered
        btnNOT.setBackground(new Color(180, 180, 180));
    }//GEN-LAST:event_btnNOTMouseEntered

    private void btnNOTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNOTMouseExited
        btnNOT.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_btnNOTMouseExited

    private void btnClearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearMouseClicked
        circuito = new Circuito("Limpiado");
        mp.setCircuito(circuito);
        circuito.setPanelReferencia(mp);
        mp.repaint();
    }//GEN-LAST:event_btnClearMouseClicked

    private void btnClearMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearMouseEntered
        btnClear.setBackground(new Color(180, 180, 180));
    }//GEN-LAST:event_btnClearMouseEntered

    private void btnClearMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearMouseExited
        btnClear.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_btnClearMouseExited

    private void btnConectarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConectarMouseClicked
        mp.setModo(MiPanel.Modo.CONEXION);
        limpiarSeleccion(); 
        mp.setComponenteSeleccionado(null);
    }//GEN-LAST:event_btnConectarMouseClicked

    private void btnConectarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConectarMouseEntered
        btnConectar.setBackground(new Color(180, 180, 180));
    }//GEN-LAST:event_btnConectarMouseEntered

    private void btnConectarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConectarMouseExited
        btnConectar.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btnConectarMouseExited

    private void btnLEDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLEDMouseClicked
        Led led = new Led(50, 50);
        if (mp.getCircuito() != null) {
            mp.getCircuito().agregarComponente(led);
        }
        mp.repaint();
    }//GEN-LAST:event_btnLEDMouseClicked

    private void btnLEDMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLEDMouseEntered
        btnLED.setBackground(new Color(180, 180, 180));
    }//GEN-LAST:event_btnLEDMouseEntered

    private void btnLEDMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLEDMouseExited
        btnLED.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btnLEDMouseExited

    private void btnSwitchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSwitchMouseClicked
        Switch sw = new Switch(50, 50);
        if (mp.getCircuito() != null) {
            mp.getCircuito().agregarComponente(sw);
        }
        mp.repaint();
    }//GEN-LAST:event_btnSwitchMouseClicked

    private void btnSwitchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSwitchMouseEntered
        btnSwitch.setBackground(new Color(180, 180, 180));
    }//GEN-LAST:event_btnSwitchMouseEntered

    private void btnSwitchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSwitchMouseExited
        btnSwitch.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btnSwitchMouseExited

    private void btnSeleccionarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSeleccionarMouseClicked
        mp.setModo(MiPanel.Modo.SELECCION);
        limpiarSeleccion(); 
    }//GEN-LAST:event_btnSeleccionarMouseClicked

    private void btnSeleccionarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSeleccionarMouseEntered
        btnSeleccionar.setBackground(new Color(180, 180, 180));
    }//GEN-LAST:event_btnSeleccionarMouseEntered

    private void btnSeleccionarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSeleccionarMouseExited
        btnSeleccionar.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btnSeleccionarMouseExited

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        try {
            if (mp.getComponenteSeleccionado() != null) {
                circuito.eliminarComponente(mp.getComponenteSeleccionado());
                limpiarSeleccion();
                mp.repaint();
            } 
            else if (mp.getConectorSeleccionado() != null) {
                circuito.eliminarConector(mp.getConectorSeleccionado());
                limpiarSeleccion();
                mp.repaint();
            } 
            else {
                throw new Exception("Seleccione un componente");
            }
        } catch (Exception ex) {
            circuito.mostrarError(ex.getMessage());
        }
    }//GEN-LAST:event_btnEliminarMouseClicked

    private void btnEliminarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseEntered
        btnEliminar.setBackground(Color.RED);
    }//GEN-LAST:event_btnEliminarMouseEntered

    private void btnEliminarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseExited
        btnEliminar.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btnEliminarMouseExited

    private void subBtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subBtnNuevoActionPerformed
        if (circuito != null && circuito.isModificado()) {
            int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Si crea un nuevo circuito sin guardar este, perderá todo lo que ha creado hasta el momento ?",
                "Guardar circuito",
                JOptionPane.YES_NO_CANCEL_OPTION);

            if (respuesta == JOptionPane.YES_OPTION) {
                Archivo.guardarCircuito(circuito, this);
                if (circuito.isModificado()) return;
            } else if (respuesta == JOptionPane.CANCEL_OPTION) {
                return;
            }
        }

        circuito = new Circuito("Nuevo Circuito");
        mp.setCircuito(circuito);
        circuito.setPanelReferencia(mp);
        mp.repaint();
    }//GEN-LAST:event_subBtnNuevoActionPerformed

    private void subBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subBtnGuardarActionPerformed
        Archivo.guardarCircuito(circuito, this);
        mp.repaint();
    }//GEN-LAST:event_subBtnGuardarActionPerformed

    private void subBtnGuardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subBtnGuardarComoActionPerformed
        Archivo.guardarCircuitoComo(circuito, this);
        mp.repaint();
    }//GEN-LAST:event_subBtnGuardarComoActionPerformed

    private void subBtnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subBtnAbrirActionPerformed

        if (circuito != null && circuito.isModificado()) {
            int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Desea guardar el circuito actual antes de abrir otro?",
                "Guardar circuito",
                JOptionPane.YES_NO_CANCEL_OPTION);

            if (respuesta == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (respuesta == JOptionPane.YES_OPTION) {
                Archivo.guardarCircuito(circuito, this);
                if (circuito.isModificado()) return;
            }
        }

        Circuito nuevoCircuito = Archivo.cargarCircuito(this);
        if (nuevoCircuito != null) {
            circuito = nuevoCircuito;
            mp.setCircuito(circuito);
            circuito.setPanelReferencia(mp);
            mp.setModo(MiPanel.Modo.SELECCION);
            limpiarSeleccion();
            mp.repaint();

            JOptionPane.showMessageDialog(this, """
                                                Circuito cargado. Puede modificarlo:
                                                - Seleccione componentes para moverlos
                                                - Use el bot\u00f3n Eliminar para borrar
                                                - Use el modo Conexi\u00f3n para agregar conexiones""",
                "Instrucciones",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_subBtnAbrirActionPerformed

    private void subBtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subBtnSalirActionPerformed
        if (circuito != null && circuito.isModificado()) {
            int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Desea guardar el circuito actual antes de salir?",
                "Guardar circuito",
                JOptionPane.YES_NO_CANCEL_OPTION);

            if (respuesta == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (respuesta == JOptionPane.YES_OPTION) {
                Archivo.guardarCircuito(circuito, this);
                if (circuito.isModificado()) return;
            }
        }
        System.exit(0);
    }//GEN-LAST:event_subBtnSalirActionPerformed

    private void panelSuperiorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSuperiorMousePressed
        //Guardo la posicion del mouse cuando mantiene presionado el click para cuando mueva la ventana
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_panelSuperiorMousePressed

    private void panelSuperiorMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSuperiorMouseDragged
        //muevo la ventana haciendo el calculo de la diferencia de pociciones con respecto a la que guardé anterior mente y la que tiene el mouse actualmente
        int x,y;
        x = evt.getXOnScreen();
        y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_panelSuperiorMouseDragged

    private void btnCerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseExited
        //Cuando sale del boton restablezco el color al anterior para esa sensacion de que es clickeable
        btnCerrar.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btnCerrarMouseExited

    private void btnCerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseEntered
        //Cambio el color cuando el mouse pasa por el boton para darle mas animacion al programa
        btnCerrar.setBackground(Color.red);
    }//GEN-LAST:event_btnCerrarMouseEntered

    private void btnCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseClicked
        //simplemente cierra todo el programa
        System.exit(0);
    }//GEN-LAST:event_btnCerrarMouseClicked

    private void subBtnAcercadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subBtnAcercadeActionPerformed
        AcercaDe acerca = new AcercaDe();
        acerca.setVisible(true);
    }//GEN-LAST:event_subBtnAcercadeActionPerformed

    private void btnEncenderSWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncenderSWActionPerformed
        if (componenteSeleccionado instanceof Switch aSwitch) {
        circuito.toggleSwitch(aSwitch);
        mp.repaint();
        }
    }//GEN-LAST:event_btnEncenderSWActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JLabel btnAND;
    private javax.swing.JLabel btnCerrar;
    private javax.swing.JLabel btnClear;
    private javax.swing.JLabel btnConectar;
    private javax.swing.JLabel btnEliminar;
    private javax.swing.JToggleButton btnEncenderSW;
    private javax.swing.JLabel btnLED;
    private javax.swing.JLabel btnNOT;
    private javax.swing.JLabel btnOR;
    private javax.swing.JLabel btnSeleccionar;
    private javax.swing.JLabel btnSwitch;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel panelHerramientas;
    private javax.swing.JPanel panelSuperior;
    private javax.swing.JPanel panelTablero;
    private javax.swing.JMenuItem subBtnAbrir;
    private javax.swing.JMenuItem subBtnAcercade;
    private javax.swing.JMenuItem subBtnGuardar;
    private javax.swing.JMenuItem subBtnGuardarComo;
    private javax.swing.JMenuItem subBtnNuevo;
    private javax.swing.JMenuItem subBtnSalir;
    // End of variables declaration//GEN-END:variables
}
