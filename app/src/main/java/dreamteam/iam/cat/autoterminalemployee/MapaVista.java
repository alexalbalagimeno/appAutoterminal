package dreamteam.iam.cat.autoterminalemployee;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Aquesta classe es per tractar tot el que fa referent al dibuix de la pantalla
 * de Mapa
 */
public class MapaVista extends View {

    private Bitmap background;
    private Display display;
    private Rect screen;
    private Paint paint;
    private List<PointMap> centerPoints;
    private int widthScreen, heightScreen;
    private double widthClassRoom = 7.22;
    private double heightClassRoom = 6.6;
    private double relationWidthAndHeightClassRoom;
    private double scale;
    Resources res;

    /**
     * Al crear el mapa Bitmap ja carrega el fons amb planolclasse
     * Carrega Display (pantalla android)
     * Crea objecte Paint per a pintar colors
     *
     * @param context de activitat Mapa
     */
    public MapaVista(Context context) {
        super(context);
        res = getResources();
        Point size = new Point();
        background = BitmapFactory.decodeResource(res, R.drawable.planolclasse);
        display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        display.getSize(size);
        //la habitacio es mes alta que ample
        relationWidthAndHeightClassRoom = heightClassRoom / widthClassRoom;
        //tamany pantalla en pixels
        widthScreen = size.x;
        heightScreen = size.y;
        //escalar metres en pixels
        scale = widthScreen / widthClassRoom;
        screen = new Rect(0, 0, widthScreen, (int) (widthScreen * relationWidthAndHeightClassRoom));
        paint = new Paint();
        //crea arrayList dels centres de punts
        centerPoints = new ArrayList();
    }

    /**
     *
     * @param canvas parametre per a dibuixar tot
     */
    @Override
    protected void onDraw(Canvas canvas) {

        //dibuixa fons blanc
        canvas.drawRGB(255, 255, 255);
        //dibuixa la imatge a la pantalla
        canvas.drawBitmap(background, null, screen, null);
        paint.setColor(Color.RED);
        //dibuixa les cel·les
        drawCells(canvas);
        //carrega llista de punts centrals
        centerPoints = loadPointsCenterAllCells();
        //dibuixa al centre de les cel·les dels punts centrals
        drawCenterAllCells(canvas);
        //dibuixa el punt del arduino
        putPositionPointArduino(canvas, LocalitzarVehicle.coordX, LocalitzarVehicle.coordY);
        //compare center all cells
        paint.setTextSize(100);
        paint.setColor(Color.BLUE);
        paint.setTextAlign(Paint.Align.CENTER);
        //put text number place
        canvas.drawText(res.getString(R.string.Row) +": "+  LocalitzarVehicle.fila, widthScreen / 2, heightScreen - 300, paint);
        canvas.drawText(res.getString(R.string.Column)+": " + LocalitzarVehicle.columna, widthScreen / 2, heightScreen - 200, paint);
    }

    /**
     *
     * @param canvas per a dibuixar el punt
     * @param coordX coordenada X
     * @param coordY coordenada Y
     */
    private void putPositionPointArduino(Canvas canvas, double coordX, double coordY) {
        paint.setColor(Color.RED);
        PointMap pointmap = new PointMap(LocalitzarVehicle.coordX, LocalitzarVehicle.coordY, LocalitzarVehicle.columna, LocalitzarVehicle.fila);
        canvas.drawCircle((int) (scale * pointmap.getX()), (int) (scale * pointmap.getY()), 10, paint);
    }

    /**
     *
     * @param canvas dibuixa punts centrals cel·les
     */
    private void drawCenterAllCells(Canvas canvas) {
        for (PointMap pointmap : centerPoints) {
            paint.setColor(Color.YELLOW);
            canvas.drawCircle((int) (scale * pointmap.x), (int) (scale * pointmap.y), 3, paint);
        }
    }

    /**
     * Dibuixa les cel·les
     * @param canvas
     */
    private void drawCells(Canvas canvas) {
        paint.setColor(Color.YELLOW);
        for (int i = 0; i < 8; i++) {
            canvas.drawLine((int) (scale * i), (int) (scale * 0), (int) (scale * i), (int) (scale * heightClassRoom), paint);
        }
        for (int i = 0; i < 7; i++) {
            canvas.drawLine((int) (scale * 0), (int) (scale * i), (int) (scale * widthClassRoom), (int) (scale * i), paint);
        }
    }

    /**
     *
     * @return List<PointMap></PointMap> que es la llista de punts centrals
     */
    private List<PointMap> loadPointsCenterAllCells() {
        List<PointMap> list = new ArrayList<>();
        PointMap pointMapCenterCell;
        int incrX = 0;
        for (int numberCell = 0; numberCell < 42; numberCell++) {
            if (numberCell < 7) {
                pointMapCenterCell = new PointMap(0.5 + incrX, 0.5);
                incrX++;
                if (incrX == 7)
                    incrX = 0;
            } else if (numberCell >= 7 && numberCell < 14) {
                pointMapCenterCell = new PointMap(0.5 + incrX, 1.5);
                incrX++;
                if (incrX == 7)
                    incrX = 0;
            } else if (numberCell >= 14 && numberCell < 21) {
                pointMapCenterCell = new PointMap(0.5 + incrX, 2.5);
                incrX++;
                if (incrX == 7)
                    incrX = 0;
            } else if (numberCell >= 21 && numberCell < 28) {
                pointMapCenterCell = new PointMap(0.5 + incrX, 3.5);
                incrX++;
                if (incrX == 7)
                    incrX = 0;
            } else if (numberCell >= 28 && numberCell < 35) {
                pointMapCenterCell = new PointMap(0.5 + incrX, 4.5);
                incrX++;
                if (incrX == 7)
                    incrX = 0;
            } else {
                pointMapCenterCell = new PointMap(0.5 + incrX, 5.5);
                incrX++;
                if (incrX == 7)
                    incrX = 0;
            }
            list.add(pointMapCenterCell);
        }
        return list;
    }

}