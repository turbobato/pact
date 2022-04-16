#pragma once

class Lamp{

    public:
    Lamp();
    Lamp(float luminosity);
    float getLuminosity();
    void  setLuminosity();


    private:

    float m_luminosity;

};
