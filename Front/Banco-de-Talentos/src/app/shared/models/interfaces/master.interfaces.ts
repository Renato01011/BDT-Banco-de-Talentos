export interface LanguageModel {
    id: number,
    name: string,
    code: string
}

export interface RoleModel {
    id: number,
    name: string,
    code: string
}

export interface CurrenciesModel {
    id: number,
    name: string,
    code: string
}

export interface ProfileModel {
    id: number,
    name: string,
    code: string
}

export interface LangProficiencyModel {
    id: number,
    name: string
}

export interface CountryModel {
    id: number,
    country: string,
    code: string,
    callingCode: string
}

export interface CityModel {
    id: number,
    city: string
}

export interface CountryCityModel {
    id: number,
    country: string,
    code: string,
    callingCode: string,
    cities: [
        {
            id: number,
            city: string
        }
    ]
}
