/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author ADMIN
 */
public class CarImage_DTO {
    private int img_id;
    private int car_id;
    private byte[] car_img;

    public CarImage_DTO() {
    }

    public CarImage_DTO(int img_id, int car_id, byte[] car_img) {
        this.img_id = img_id;
        this.car_id = car_id;
        this.car_img = car_img;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public byte[] getCar_img() {
        return car_img;
    }

    public void setCar_img(byte[] car_img) {
        this.car_img = car_img;
    }
}
