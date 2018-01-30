package MathLib;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author stefan
 */
public class Testclass {
    public static void main(String[] args){
        
        Normal3 n123 = new Normal3(1.0, 2.0, 3.0);
        Normal3 n321 = new Normal3(3.0, 2.0, 1.0);
        Normal3 n100 = new Normal3(1.0, 0.0, 0.0);
        Normal3 n010 = new Normal3(0.0, 1.0, 0.0);
        
        Vector3 v100 = new Vector3(1.0, 0.0, 0.0);
        Vector3 v010 = new Vector3(0.0, 1.0, 0.0);
        Vector3 v111 = new Vector3(1.0, 1.0, 1.0);
        Vector3 v432 = new Vector3(4.0, 3.0, 2.0);
        Vector3 v220 = new Vector3(2.0, 2.0, 0.0);
        Vector3 v123 = new Vector3(1.0, 2.0, 3.0);
        Vector3 v321 = new Vector3(3.0, 2.0, 1.0);
        Vector3 v888 = new Vector3(8.0, 8.0, 8.0);
        Vector3 v_070707070 = new Vector3(-0.707, 0.707, 0);
        Vector3 v070707070 = new Vector3(0.707, 0.707, 0);
        
        Point3 p111 = new Point3(1.0, 1.0, 1.0);
        Point3 p220 = new Point3(2.0, 2.0, 0.0);
        Point3 p321 = new Point3(3.0, 2.0, 1.0);
        
        
        Mat3x3 m100010001 = new Mat3x3(1, 0, 0, 0, 1, 0, 0, 0, 1);
        Mat3x3 m123456789 = new Mat3x3(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Mat3x3 m001010100 = new Mat3x3(0, 0, 1, 0, 1, 0, 1, 0, 0);
        
        Normal3 result = n123.mul(0.5);
        System.out.println("n123 * 0.5 = " + result.x + " " + result.y + " " + result.z);
        result = n123.add(n321);
        System.out.println("n123 + n321 = " + result.x + " " + result.y + " " + result.z);
        double scalarp = n100.dot(v100);
        System.out.println("n100 dot v100 = " + scalarp);
        scalarp = v100.dot(n100);
        System.out.println("v100 dot n100 = " + scalarp);
        scalarp = v100.dot(v100);
        System.out.println("v100 dot v100 = " + scalarp);
        Vector3 subsub = p111.sub(p220);
        System.out.println("p111 - p220 = " + subsub.x + " " + subsub.y + " " + subsub.z);
        Point3 kubkub = p111.sub(v432);
        System.out.println("p111 - v432 = " + kubkub.x + " " + kubkub.y + " " + kubkub.z);
        kubkub = p111.add(v432);
        System.out.println("p111 + v432 = " + kubkub.x + " " + kubkub.y + " " + kubkub.z);
        System.out.println("Magnitude of v111 = " + v111.magnitude );
        subsub = v123.add(n321);
        System.out.println("v123 + n321 = " + subsub.x + " " + subsub.y + " " + subsub.z);
        subsub =v123.add(v321);
        System.out.println("v123 + v321 = " + subsub.x + " " + subsub.y + " " + subsub.z);
        subsub = v111.sub(n010);
        System.out.println("v111 - n010 = " + subsub.x + " " + subsub.y + " " + subsub.z);
        subsub = v123.mul(0.5);
        System.out.println("v123 * 0.5 = " + subsub.x + " " + subsub.y + " " + subsub.z);
        subsub = v_070707070.reflectedOn(n010);
        System.out.println("v_070707070 reflect on n010 = " + subsub.x + " " + subsub.y + " " + subsub.z);
        subsub = v070707070.reflectedOn(n100);
        System.out.println("v070707070 reflect on n100 = " + subsub.x + " " + subsub.y + " " + subsub.z);
        Vector3 matv = m100010001.mul(v321);
        System.out.println("m100010001 * v321 = " + matv.x + " " + matv.y + " " + matv.z);
        Point3 matp = m100010001.mul(p321);
        System.out.println("m100010001 * p321 = " + matp.x + " " + matp.y + " " + matp.z);
        Mat3x3 gnop = m123456789.mul(m100010001);
        System.out.println("m123456789 * m100010001 = \n"+gnop.m11+" "+gnop.m12+" "+gnop.m13+"\n"+gnop.m21+" "+gnop.m22+" "+gnop.m23+"\n"+gnop.m31+" "+gnop.m32+" "+gnop.m33);
        gnop = m123456789.mul(m001010100);
        System.out.println("m123456789 * m001010100 = \n"+gnop.m11+" "+gnop.m12+" "+gnop.m13+"\n"+gnop.m21+" "+gnop.m22+" "+gnop.m23+"\n"+gnop.m31+" "+gnop.m32+" "+gnop.m33);
        gnop = m123456789.changeCol1(v888);
        System.out.println("m123456789 changeCol1(v888) = \n"+gnop.m11+" "+gnop.m12+" "+gnop.m13+"\n"+gnop.m21+" "+gnop.m22+" "+gnop.m23+"\n"+gnop.m31+" "+gnop.m32+" "+gnop.m33);
        gnop = m123456789.changeCol1(v888);
        System.out.println("m123456789 changeCol1(v888) = \n"+gnop.m11+" "+gnop.m12+" "+gnop.m13+"\n"+gnop.m21+" "+gnop.m22+" "+gnop.m23+"\n"+gnop.m31+" "+gnop.m32+" "+gnop.m33);
        gnop = m123456789.changeCol2(v888);
        System.out.println("m123456789 changeCol2(v888) = \n"+gnop.m11+" "+gnop.m12+" "+gnop.m13+"\n"+gnop.m21+" "+gnop.m22+" "+gnop.m23+"\n"+gnop.m31+" "+gnop.m32+" "+gnop.m33);
        gnop = m123456789.changeCol3(v888);
        System.out.println("m123456789 changeCol3(v888) = \n"+gnop.m11+" "+gnop.m12+" "+gnop.m13+"\n"+gnop.m21+" "+gnop.m22+" "+gnop.m23+"\n"+gnop.m31+" "+gnop.m32+" "+gnop.m33);
        
        System.out.println("toString Test m123456789 : \n" + m123456789.toString());
        System.out.println("equals Test m123456789 : \n" + m123456789.equals(m123456789));
        System.out.println("toString Test v100 : \n" + v100.toString());
        System.out.println("toString Test p220 : \n" + p220.toString());
        System.out.println("equals Test v100/p220 : \n" + v100.equals(p220));
    }
}
