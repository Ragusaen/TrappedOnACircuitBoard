#include <dumpling.h>


Bool global_state = LOW;

TaskHandle_t TaskAnonymousModule0_Handle;
Bool TaskAnonymousModule0_running = 1;
void TaskAnonymousModule0(void *pvParameters) {
    while (1) {
        {
            global_state = !global_state;
            pinMode(4, OUTPUT);
            digitalWrite(4, global_state);
        }
        vTaskSuspend(TaskAnonymousModule0_Handle);
    }
}
void ControllerTask(void *pvParameters) {
unsigned long AnonymousModule0LastValue = 0;

while (1) {
int e;

if (TaskAnonymousModule0_running && millis() - AnonymousModule0LastValue >= 500) {
AnonymousModule0LastValue = millis();
vTaskResume(TaskAnonymousModule0_Handle);
}
}
}
void setup() {
xTaskCreate(TaskAnonymousModule0, "AnonymousModule0", 128, NULL, 0, &TaskAnonymousModule0_Handle );
vTaskSuspend(TaskAnonymousModule0_Handle);
xTaskCreate(ControllerTask, "Controller", 128, NULL, 0, NULL);
}


 void loop() {}
