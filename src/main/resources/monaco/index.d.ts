interface Api {
    on(name: string): boolean;
    off(name: string): boolean;
}

interface AppData {

}

declare class Test {
    static on(name: string): boolean;
    static off(name: string): boolean;
}