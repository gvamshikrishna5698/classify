export class ClassificationResponse {
    constructor(
        public requestOwner: string,
        public requestCategory: string,
        public totalKeywords: number,
        public matchedKeywords: number,
        public confidence: number,
        public docType: string,
        public requestStatus: string,
        public dateCreated: Date,
        public classificationResult: string
    ){}
}
