export interface NewTalentModel {
    nombre: string,
    apellidoPaterno: string,
    apellidoMaterno: string,
    fotoDePerfil: string,
    documentos: [
        {
            nombre: string,
            tipoArchivo: string,
            archivo: string,
        }
    ],
    descripcion: string,
    idPuestoActual: number,
    idPais: number,
    idCiudad: number,
    linkedin: string,
    github: string,
    tipoMoneda: string,
    montoInicial: number,
    montoFinal: number,
    celular: string,
    habilidadesTecnicas: TechnicalAbilitiesTalentModel[],
    habilidadesBlandas: SoftSkillsTalentModel[],
    experienciasLaborales: WorkExperienceTalentModel[],
    experienciasEducativas: EducationalExperienceTalentModel[],
    idiomas: LanguagesTalentModel[],
}

export interface TechnicalAbilitiesTalentModel {
    nombre: string,
    anios: number,
}

export interface SoftSkillsTalentModel {
    nombre: string,
}

export interface WorkExperienceTalentModel {
    empresa: string,
    puesto: string,
    fechaInicio: Date,
    fechaFin: Date,
}

export interface EducationalExperienceTalentModel {
    institucion: string,
    carrera: string,
    grado: string,
    fechaInicio: Date,
    fechaFin: Date,
}

export interface LanguagesTalentModel {
    idiomaId: number,
    nivelId: number,
    nuEstrellas: number,
}