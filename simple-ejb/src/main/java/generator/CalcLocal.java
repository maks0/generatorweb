/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import javax.ejb.Local;

/**
 *
 * @author maks
 */
@Local
public interface CalcLocal {
    int plus (int a, int b);
}
