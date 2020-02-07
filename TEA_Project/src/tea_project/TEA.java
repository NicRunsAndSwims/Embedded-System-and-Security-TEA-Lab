
package tea_project;

import java.util.ArrayList;
import java.util.Arrays;
import gnu.io.*;

public class TEA {
    
    private int[] v;
    private int[] k;
    
    
    public TEA(int[] v, int[] k) {
        
        this.v = v;
        this.k = k;
        
    }
    
    void encrypt(int[] v, int[] k) {
        
    int v0 = v[0], v1 = v[1], sum = 0;
    int delta=0x9e3779b9; /* a key schedule constant */
    int k0=k[0], k1=k[1], k2=k[2], k3=k[3]; /* cache key */
    
    for (int i=0; i < 32; i++) { /* basic cycle start */
        sum += delta;
        v0 += ((v1<<4) + k0) ^ (v1 + sum) ^ ((v1>>>5) + k1);
        v1 += ((v0<<4) + k2) ^ (v0 + sum) ^ ((v0>>>5) + k3);
    } /* end cycle */
    
    v[0]=v0; v[1]=v1;
    
    }
    void decrypt(int[] v, int[] k) {
        
    
        int v0=v[0], v1=v[1], sum=0xC6EF3720, i; /* set up */
        int delta=0x9e3779b9; /* a key schedule constant */
        int k0=k[0], k1=k[1], k2=k[2], k3=k[3]; /* cache key */
        
        for (i=0; i<32; i++) { /* basic cycle start */
            v1 -= ((v0<<4) + k2) ^ (v0 + sum) ^ ((v0>>>5) + k3);
            v0 -= ((v1<<4) + k0) ^ (v1 + sum) ^ ((v1>>>5) + k1);
            sum -= delta;
        } /* end cycle */

        v[0]=v0; v[1]=v1;
    }

    public int getV0() {
        return this.v[0];
    }
    public int getV1() {
        return this.v[1];
    }

    public void setV(int[] v) {
        this.v = v;
    }

    public int[] getK() {
        return this.k;
    }

    public void setK(int[] k) {
        this.k = k;
    }

    @Override
    public String toString() {
        return "TEA{" + "v=" + v + ", k=" + k + '}';
    }
    
}
