#pragma once
class Pump
{
private:
    bool state;
public:
    Pump();
    Pump(bool state);
    void setState();
    bool getState();
};


