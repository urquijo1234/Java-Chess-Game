package ajedrezjuego;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Joseph Urquijo
 */
public class ChessGame {

    public static LinkedList<Pieza> ps = new LinkedList<>();
    public static Pieza selectedPiece = null;

    public static void main(String[] args) throws IOException {

        /*
        Descargar la imagen proporcionada en la descripcion e indicar la ruta correcta de descarga
         */
        BufferedImage all = ImageIO.read(new File("C:\\Users\\josep\\Downloads\\chess.png"));
        Image imgs[] = new Image[12];
        int ind = 0;
        for (int y = 0; y < 400; y += 200) {
            for (int x = 0; x < 1200; x += 200) {
                imgs[ind] = all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                ind++;
            }
        }
        Pieza brook = new Pieza(0, 0, false, "torre", ps);
        Pieza bkinght = new Pieza(1, 0, false, "caballo", ps);
        Pieza bbishop = new Pieza(2, 0, false, "alfil", ps);
        Pieza bqueen = new Pieza(3, 0, false, "reina", ps);
        Pieza bking = new Pieza(4, 0, false, "rey", ps);
        Pieza bbishop2 = new Pieza(5, 0, false, "alfil", ps);
        Pieza bkight2 = new Pieza(6, 0, false, "caballo", ps);
        Pieza brook2 = new Pieza(7, 0, false, "torre", ps);
        Pieza bpawn1 = new Pieza(1, 1, false, "peon", ps);
        Pieza bpawn2 = new Pieza(2, 1, false, "peon", ps);
        Pieza bpawn3 = new Pieza(3, 1, false, "peon", ps);
        Pieza bpawn4 = new Pieza(4, 1, false, "peon", ps);
        Pieza bpawn5 = new Pieza(5, 1, false, "peon", ps);
        Pieza bpawn6 = new Pieza(6, 1, false, "peon", ps);
        Pieza bpawn7 = new Pieza(7, 1, false, "peon", ps);
        Pieza bpawn8 = new Pieza(0, 1, false, "peon", ps);

        Pieza wrook = new Pieza(0, 7, true, "torre", ps);
        Pieza wkinght = new Pieza(1, 7, true, "caballo", ps);
        Pieza wbishop = new Pieza(2, 7, true, "alfil", ps);
        Pieza wqueen = new Pieza(3, 7, true, "reina", ps);
        Pieza wking = new Pieza(4, 7, true, "rey", ps);
        Pieza wbishop2 = new Pieza(5, 7, true, "alfil", ps);
        Pieza wkight2 = new Pieza(6, 7, true, "caballo", ps);
        Pieza wrook2 = new Pieza(7, 7, true, "torre", ps);
        Pieza wpawn1 = new Pieza(1, 6, true, "peon", ps);
        Pieza wpawn2 = new Pieza(2, 6, true, "peon", ps);
        Pieza wpawn3 = new Pieza(3, 6, true, "peon", ps);
        Pieza wpawn4 = new Pieza(4, 6, true, "peon", ps);
        Pieza wpawn5 = new Pieza(5, 6, true, "peon", ps);
        Pieza wpawn6 = new Pieza(6, 6, true, "peon", ps);
        Pieza wpawn7 = new Pieza(7, 6, true, "peon", ps);
        Pieza wpawn8 = new Pieza(0, 6, true, "peon", ps);

        JFrame frame = new JFrame();
        frame.setBounds(10, 10, 512, 512);
        frame.setUndecorated(true);
        JPanel pn = new JPanel() {
            @Override
            public void paint(Graphics g) {
                boolean white = true;
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {
                        if (white) {
                            g.setColor(new Color(235, 235, 208));
                        } else {
                            g.setColor(new Color(119, 148, 85));
                        }
                        g.fillRect(x * 64, y * 64, 64, 64);
                        white = !white;
                    }
                    white = !white;
                }
                for (Pieza p : ps) {
                    int ind = 0;
                    if (p.name.equalsIgnoreCase("rey")) {
                        ind = 0;
                    }
                    if (p.name.equalsIgnoreCase("reina")) {
                        ind = 1;
                    }
                    if (p.name.equalsIgnoreCase("alfil")) {
                        ind = 2;
                    }
                    if (p.name.equalsIgnoreCase("caballo")) {
                        ind = 3;
                    }
                    if (p.name.equalsIgnoreCase("torre")) {
                        ind = 4;
                    }
                    if (p.name.equalsIgnoreCase("peon")) {
                        ind = 5;
                    }
                    if (!p.isWhite) {
                        ind += 6;
                    }
                    g.drawImage(imgs[ind], p.x, p.y, this);

                }

            }

        };
        frame.add(pn);
        frame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedPiece != null) {
                    selectedPiece.x = e.getX()-32;
                    selectedPiece.y = e.getY()-32;
                    frame.repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });

        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //System.out.println((getPieza(e.getX(), e.getY()).isWhite?" Blanco ":" Negro") +getPieza(e.getX(), e.getY()).name);
                selectedPiece = getPieza(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                selectedPiece.move(e.getX() / 64, e.getY() / 64);
                frame.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }

    public static Pieza getPieza(int x, int y) {
        int xp = x / 64;
        int yp = y / 64;
        for (Pieza p : ps) {
            if (p.xp == xp && p.yp == yp) {
                return p;
            }
        }
        return null;
    }

}
